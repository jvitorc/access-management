import { Profile } from "./Profile";

export interface Account {

    id: number;
    name: string;
    email: string;
    profile: Profile;
    edit: any;
}
