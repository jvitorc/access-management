import React from "react";
import { AuthContextType } from "./auth-interface";

const AuthContext = React.createContext<AuthContextType>(null!);

/**
 * Hook to access the current user data and functions to sign in and sign out.
 * @returns {AuthContextType} The current user data and functions to sign in and sign out.
 */
function useAuth(): AuthContextType {
    return React.useContext<AuthContextType>(AuthContext);
}

export {AuthContext, useAuth}