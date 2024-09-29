import React from "react";
import { Menu } from 'primereact/menu';
import { Link, useNavigate } from "react-router-dom";

export default function NavBar() {
    let navigate = useNavigate();
    const items = [
        { label: 'Home', icon: 'pi pi-home', command: () => navigate('/') },
        { label: 'Account', icon: 'pi pi-shopping-cart', command: () => navigate('/account') }, 
        { label: 'Profile', icon: 'pi pi-user', command: () => navigate('/profile') },
        { label: 'Rule', icon: 'pi pi-shopping-cart', command: () => navigate('/rule') },
        { label: 'Permission', icon: 'pi pi-shopping-cart', command: () => navigate('/permission') },
    ];
  
    return (
        <div>
            <Menu model={items} className="c-sidebar"/>
        </div>
    );
  }