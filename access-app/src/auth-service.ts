import { useAuth } from "./auth-context";
import { URL_LOGIN, URL_LOGOUT } from "./server";

const authService = {
    async signin(email: string, password: string) {
      const response = await fetch(URL_LOGIN, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({email, password}),
      })

      if (!response.ok) {
        throw "Failed to sign in";
      }
      return await response.json();
    },
    async signout(token: string) {
      const response = await fetch(URL_LOGOUT, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,},        
      })
    },
  };
  
  export { authService };
  