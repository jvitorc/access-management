import React, { useState, useEffect } from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { useAuth } from "../auth/auth-context";
import AccountService from "../service/account-service";
import DataSearch from "../components/DataSearch";
import { newPageBuild } from "../util/builder";

export default function AccountPage() {
  let auth = useAuth();

  const [page, setPage] = React.useState<any>(newPageBuild());
  const columns = [
    { field: "id", header: "Id" },
    { field: "name", header: "Name" },
    { field: "email", header: "Email" },
    { field: "profile", header: "Profile" },
    { field: "edit", header: "Edit" },
  ];

  async function find(pageNumber: number) {
    let data = await AccountService.findAccounts(
      auth.userData.access_token,
      pageNumber
    );

    data.content = data.content.map(({ id, name, email, profile }) => ({
      id,
      name,
      email,
      profile: profile.name,
      edit: <Button label="" icon="pi pi-pencil" onClick={() => {}} />,
    })) as any;

    setPage(data);
  }

  useEffect(() => {
    find(0);
  }, []);

  return (
    <React.Fragment>
      <h1 style={{ textAlign: "center" }}>Account</h1>
      <DataSearch columns={columns} page={page} findFunction={find} />
    </React.Fragment>
  );
}
