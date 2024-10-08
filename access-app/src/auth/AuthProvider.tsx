import React from "react";
import { authService } from "./auth-service";
import { AuthContext } from "./auth-context";
import { UserDataType } from "./auth-interface";


export default function AuthProvider({ children }: { children: React.ReactNode }) {
    let [userData, setUserData] = React.useState<UserDataType>(null!);
  

    /**
     * Signs in the user with the given username and password.
     * @param {string} username
     * @param {string} password
     * @returns {void}
     */
    const signin = async (username: string, password : string) => {
      const newUserData = await authService.signin(username, password);
      setUserData(newUserData);
      console.log("signin")
      console.log(userData)
    };
  
    const signout = async () => {
      if (!userData?.access_token) return;

      await authService.signout(userData.access_token);
      setUserData(null!);
      console.log("signout")
    };

    const register = async (name: string, email: string, password: string) => {
      const newUserData = await authService.register(name, email, password);
      console.log("register")
      console.log(newUserData)
      setUserData(newUserData);
    };
  
    let value = { userData, signin, signout, register };
  
    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
  }
  
  