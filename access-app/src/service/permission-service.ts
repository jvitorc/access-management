import { useAuth } from "../auth/auth-context";
import { Account } from "../interface/Account";
import { Page } from "../interface/Page";
import { Permission } from "../interface/Permission";
import { URL_PERMISSION } from "../server";

const PermissionService = {

    async findPermissions(access_token: string, pageNumber: number): Promise<Page<Permission>> {
        const response = await fetch(URL_PERMISSION + `?pageNumber=${pageNumber}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${access_token}`,
            },
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json();
    }
}

export default PermissionService;