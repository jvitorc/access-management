import { useAuth } from "../auth/auth-context";
import { Account } from "../interface/Account";
import { Page } from "../interface/Page";
import { Permission } from "../interface/Permission";
import { Rule } from "../interface/Rule";
import { URL_RULE } from "../server";

const RuleService = {

    async find(access_token: string, pageNumber: number): Promise<Page<Rule>> {
        const response = await fetch(URL_RULE + `?pageNumber=${pageNumber}`, {
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

export default RuleService;