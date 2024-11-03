import { Page } from "../interface/Page";
import { Profile } from "../interface/Profile";
import { URL_PROFILE } from "../server";

const ProfileService = {

    async find(access_token: string, pageNumber: number): Promise<Page<Profile>> {
        const response = await fetch(URL_PROFILE + `?pageNumber=${pageNumber}`, {
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

export default ProfileService;