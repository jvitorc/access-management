import React, { useState, useEffect } from "react";
import { DataTable } from "primereact/datatable";
import { Button } from "primereact/button";
import { useAuth } from "../auth/auth-context";
import PermissionService from "../service/permission-service";
import { Permission } from "../interface/Permission";
import { Page } from "../interface/Page";
import { newPageBuild } from "../util/builder";
import DataSearch from "../components/DataSearch";
import { Rule } from "../interface/Rule";
import RuleService from "../service/rule-service";

export default function RulePage() {
    let auth = useAuth();
  
    const [page, setPage] = React.useState<Page<Rule>>(newPageBuild());
  
    const columns = [
      { field: "id", header: "Id" },
      { field: "name", header: "Name" },
      { field: "description", header: "Description" },
      { field: "permissions", header: "Permissions" },
      { field: "edit", header: "Edit" },
    ];
  
    useEffect(() => {
      find(0)
    }, []);
  
    async function find(pageNumber: number) {
      let data = await RuleService.find(auth.userData.access_token, pageNumber)    
  
      data.content = data.content.map(({ id, name, description, permissions}) => ({
          id, name, description,
          permissions: permissions.length,
          edit: <Button label="" icon="pi pi-pencil" onClick={() => {}} />,
      })) as any;
  
      setPage(data)
    }
  
    return (
      <React.Fragment>
        <h1 style={{ textAlign: "center" }}>Rules</h1>
        <DataSearch columns={columns} page={page} findFunction={find} />
      </React.Fragment>
    );
  }
  