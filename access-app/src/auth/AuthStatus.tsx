import React from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "./auth-context";


export default function AuthStatus() {
    let auth = useAuth();
    let navigate = useNavigate();
  
    if (!auth.userData) {
      return <p>You are not logged in.</p>;
    }
  
    return (
      <span>
        Welcome {auth.userData.name}!{" "}
        <button
          onClick={async () => {
            await auth.signout();
            navigate("/");
          }}
        >
          Sign out
        </button>
      </span>
    );
  }
  