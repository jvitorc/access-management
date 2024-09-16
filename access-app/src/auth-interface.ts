export interface AuthContextType {
    userData: UserDataType;
    signin: (username: string, password : string) => void;
    signout: () => void;
}

export interface UserDataType {
    name: string;
    email: string;
    access_token: string;
}