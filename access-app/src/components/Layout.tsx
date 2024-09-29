import React from "react";
import { Outlet } from "react-router-dom";
import AuthStatus from "../auth/AuthStatus";
import NavBar from "./NavBar";


export default function Layout() {
    return (
      <div className="c-layout">
        <div className="c-heading">
          <AuthStatus />
        </div>
          <NavBar />
        <section className="c-content">
          <Outlet />
        </section>
        <footer className="c-footer">João Vitor Cardoso [2024]</footer>
      </div>
    );
  }
  