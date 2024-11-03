import React, { useState, useEffect } from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { useAuth } from "../auth/auth-context";
import PermissionService from "../service/permission-service";
import { Paginator } from "primereact/paginator";
import { Permission } from "../interface/Permission";
import { Page } from "../interface/Page";
import { newPageBuild } from "../util/builder";
import DataSearch from "../components/DataSearch";
import { useNavigate } from "react-router-dom";

export default function PermissionPage() {
  let auth = useAuth();
  let navigate = useNavigate();
  
  const [page, setPage] = React.useState<Page<Permission>>(newPageBuild());

  const columns = [
    { field: "id", header: "Id" },
    { field: "name", header: "Name" },
    { field: "edit", header: "Edit" },
  ];

  useEffect(() => {
    find(0)
  }, []);

  async function find(pageNumber: number) {
    let data = await PermissionService.findPermissions(auth.userData.access_token, pageNumber)    

    data.content = data.content.map(({ id, name }) => ({
        id, name,
        edit: <Button label="" icon="pi pi-pencil" onClick={() => navigate(`/permission/edit`)} />,
    }));

    setPage(data)
  }

  return (
    <React.Fragment>
      <h1 style={{ textAlign: "center" }}>Permission</h1>
      <DataSearch columns={columns} page={page} findFunction={find} />
    </React.Fragment>
  );
}
