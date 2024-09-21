export interface AuthContextType {
    userData: UserDataType;
    signin: (username: string, password : string) => void;
    signout: () => void;
    register: (name: string, email: string, password: string) => void;
}

export interface UserDataType {
    name: string;
    email: string;
    access_token: string;
}