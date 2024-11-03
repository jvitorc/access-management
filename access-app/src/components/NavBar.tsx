import React, { useRef } from "react";
import { Menu } from "primereact/menu";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../auth/auth-context";
import { confirmPopup } from "primereact/confirmpopup";
import { Button } from "primereact/button";
import { Toast } from "primereact/toast";
import { ConfirmDialog, confirmDialog } from "primereact/confirmdialog";

export default function NavBar() {
  let navigate = useNavigate();
  let auth = useAuth();
  if (auth.userData === null) {
    navigate("/login", { replace: true });
  }
  const toast = useRef<Toast>(null);

  const accept = () => {
    auth.signout();
    navigate('/login', { replace: true });
  };

  const confirm1 = () => {
    confirmDialog({
      message: "Are you sure you want to proceed?",
      header: "Confirmation",
      icon: "pi pi-exclamation-triangle",
      defaultFocus: "accept",
      acceptClassName: 'p-button-danger',
      rejectClassName: 'p-button-info',
      accept,
      reject: () => {},
    });
  };

  const items = [
    { label: "Home", icon: "pi pi-home", command: () => navigate("/") },
    {
      label: "Account",
      icon: "pi pi-user",
      command: () => navigate("/account"),
    },
    {
      label: "Profile",
      icon: "pi pi-folder-open",
      command: () => navigate("/profile"),
    },
    { label: "Rule", icon: "pi pi-file", command: () => navigate("/rule") },
    {
      label: "Permission",
      icon: "pi pi-check-circle",
      command: () => navigate("/permission"),
    },
    { label: "Logout", icon: "pi pi-sign-out", command: () => confirm1() },
  ];

  return (
    <>
      <Toast ref={toast} />
      <ConfirmDialog />
      <Menu model={items} className="c-sidebar" />
    </>
  );
}
