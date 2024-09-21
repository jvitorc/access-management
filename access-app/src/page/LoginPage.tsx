import React from "react";
import { useNavigate, useLocation, Link } from "react-router-dom";
import { useAuth } from "../auth/auth-context";
import { Card } from "primereact/card";
import { InputText } from "primereact/inputtext";
import { FloatLabel } from "primereact/floatlabel";
import { Password } from "primereact/password";
import { Button } from "primereact/button";

export default function LoginPage() {
  let navigate = useNavigate();
  let auth = useAuth();

  const [email, setEmail] = React.useState<string>("");
  const [password, setPassword] = React.useState<string>("");

  async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();
    await auth.signin(email, password);
    navigate("/home", { replace: true });
  }

  async function handleSignUp() {
    navigate("/signup", { replace: true });
  }

  return (
    <div className="c-card-container">
      <Card className="" title="">
        <form onSubmit={handleSubmit}>
          <div style={{ margin: "2rem" }}>
            <FloatLabel>
              <InputText
                id="username"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
              <label htmlFor="username">Username</label>
            </FloatLabel>
          </div>

          <div style={{ margin: "2rem" }}>
            <FloatLabel>
              <Password
                id="password"
                value={password}
                feedback={false}
                onChange={(e) => setPassword(e.target.value)}
              />
              <label htmlFor="password">Password</label>
            </FloatLabel>
          </div>

          <Button
            type="submit"
            style={{
              width: "200px",
              marginLeft: "2rem",
              marginRight: "2rem",
              marginBottom: "2rem",
              backgroundColor: "rosybrown",
              borderColor: "rosybrown",
              fontWeight: "bolder"
            }}
          >
            Login In
          </Button>
        </form>
        <hr />
        <Button
            type="submit"
            style={{
              width: "200px",
              margin: "2rem",
              backgroundColor: "darksalmon",
              borderColor: "darksalmon",
              fontWeight: "bolder"
            }}
            onClick={handleSignUp}
          >
            Sign Up
          </Button>

      </Card>
    </div>
  );
}
