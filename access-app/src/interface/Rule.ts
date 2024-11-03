import { Permission } from "./Permission";
import { Profile } from "./Profile";

export interface Rule {

    id: number;
    name: string;
    description: string;
    permissions: Permission[];
}
