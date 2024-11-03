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
        <span className="avatar-container" style={{ top: '30px'}}>
        <Button size="small" style={{ marginRight: '0.5em', marginBottom: '0.3em' }} onClick={async () => {
            await auth.signout();
            navigate("/");
          }}>
          Sign out
        </Button>
        <Avatar label="J" className="avatar" size="large" style={{ backgroundColor: '#9c27b0', color: '#ffffff' }} shape="circle" />
        </span>

      </div>
    );
  }
  