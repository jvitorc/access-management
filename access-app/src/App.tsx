import * as React from "react";
import "primereact/resources/themes/saga-green/theme.css";
import "primeicons/primeicons.css";

import { Routes, Route, useLocation, Navigate } from "react-router-dom";

import { useAuth } from "./auth/auth-context";
import ProtectedPage from "./ProtectedPage";
import LoginPage from "./page/LoginPage";
import AuthProvider from "./auth/AuthProvider";
import SingUpPage from "./page/SingUpPage";
import Layout from "./components/Layout";
import RequireAuth from "./auth/RequireAuth";

export default function App() {
  return (
    <AuthProvider>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SingUpPage />} />
        <Route element={<Layout />}>
          <Route
            path="/"
            element={
              <RequireAuth>
                <ProtectedPage />
              </RequireAuth>
            }
          />
          <Route
            path="/account"
            element={
              <RequireAuth>
                <ProtectedPage />
              </RequireAuth>
            }
          />
          <Route path="/profile" element={<ProtectedPage />} />
          <Route path="/rule" element={<ProtectedPage />} />
          <Route path="/permission" element={<ProtectedPage />} />
        </Route>
      </Routes>
    </AuthProvider>
  );
}
