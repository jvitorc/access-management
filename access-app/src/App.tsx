import * as React from "react";
import "primereact/resources/themes/saga-green/theme.css";
import "primeicons/primeicons.css";

import { Routes, Route, useLocation, Navigate } from "react-router-dom";

import LoginPage from "./page/LoginPage";
import AuthProvider from "./auth/AuthProvider";
import SingUpPage from "./page/SingUpPage";
import Layout from "./components/Layout";
import RequireAuth from "./auth/RequireAuth";
import AccountPage from "./page/AccountPage";
import PermissionPage from "./page/PermissionPage";
import RulePage from "./page/RulePage";
import ProfilePage from "./page/ProfilePage";
import HomePage from "./page/HomePage";

export default function App() {
  return (
    <AuthProvider>
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SingUpPage />} />
        <Route element={<RequireAuth />}>
          <Route element={<Layout />}>
            <Route path="/" element={<HomePage />} />
            <Route path="/account" element={<AccountPage />} />
            <Route path="/profile" element={<ProfilePage />} />
            <Route path="/rule" element={<RulePage />} />
            <Route path="/permission" element={<PermissionPage />} />
          </Route>
        </Route>
      </Routes>
    </AuthProvider>
  );
}
