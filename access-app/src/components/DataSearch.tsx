import { Column } from "primereact/column";
import { DataTable } from "primereact/datatable";
import { Paginator } from "primereact/paginator";
import React from "react";

export default function DataSearch({columns, page, findFunction}) {
  return (
        <div className="card">
        <DataTable value={page.content} tableStyle={{ minWidth: "50rem" }}>
          {columns.map((col, ) => (
            <Column key={col.field} field={col.field} header={col.header} />
          ))}
        </DataTable>
        {
          page.totalPages > 1 && (
            <Paginator first={page.pageable.offset} rows={page.pageable.pageSize} totalRecords={page.totalElements} onPageChange={(e) => findFunction(e.page)} />
          )
        }
        </div>
    );
  }
  