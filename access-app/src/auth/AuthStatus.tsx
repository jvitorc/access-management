import React from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "./auth-context";
import { Avatar } from 'primereact/avatar';
import { Button } from "primereact/button";


export default function AuthStatus() {
    let auth = useAuth();
    let navigate = useNavigate();
  
    if (!auth.userData) {
      navigate("/login", { replace: true });
    }
  
    return (
      <div>
        <h1 className="flex-auto">
          ACCESS MANAGEMENT
        </h1>
        <Avatar label="J" className="avatar" size="large" shape="circle" />
      </div>
    );
  }
  