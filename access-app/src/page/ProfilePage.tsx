import { Button } from "primereact/button";
import React, { useState, useEffect } from "react";
import { useAuth } from "../auth/auth-context";
import DataSearch from "../components/DataSearch";
import { Page } from "../interface/Page";
import { Rule } from "../interface/Rule";
import ProfileService from "../service/profile-service";
import { newPageBuild } from "../util/builder";
import { Profile } from "../interface/Profile";

export default function ProfilePage() {
    let auth = useAuth();
  
    const [page, setPage] = React.useState<Page<Profile>>(newPageBuild());
  
    const columns = [
      { field: "id", header: "Id" },
      { field: "name", header: "Name" },
      { field: "description", header: "Description" },
      { field: "edit", header: "Edit" },
    ];
  
    useEffect(() => {
      find(0)
    }, []);
  
    async function find(pageNumber: number) {
      let data = await ProfileService.find(auth.userData.access_token, pageNumber)    
  
      data.content = data.content.map(({ id, name, description}) => ({
          id, name, description,
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
