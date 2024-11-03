import { useAuth } from "../auth/auth-context";
import { Account } from "../interface/Account";
import { Page } from "../interface/Page";
import { URL_ACCOUNT } from "../server";

const AccountService = {

    async findAccounts(access_token: string, pageNumber: number): Promise<Page<Account>> {
        const response = await fetch(URL_ACCOUNT + `?pageNumber=${pageNumber}`	, {
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

export default AccountService;