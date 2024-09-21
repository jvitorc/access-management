import React from "react";
import { useNavigate, useLocation, Link } from "react-router-dom";
import { Card } from "primereact/card";
import { InputText } from "primereact/inputtext";
import { FloatLabel } from "primereact/floatlabel";
import { Password } from "primereact/password";
import { Button } from "primereact/button";
import { useAuth } from "../auth/auth-context";

export default function SingUpPage() {
  const [email, setEmail] = React.useState<string>("");
  const [name, setName] = React.useState<string>("");
  const [password, setPassword] = React.useState<string>("");
  const [error, setError] = React.useState<boolean>(false);

  const auth = useAuth();

  let location = useLocation();
  let from = location.state?.from?.pathname || "/home";

  let navigate = useNavigate();

  function validateEmail() {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    setError(re.test(email) == false);
  }

  async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();

    await auth.register(name, email, password);



    navigate(from, { replace: true });
  }

  return (
    <div className="c-card-container">
      <Card className="" title="">
        <form onSubmit={handleSubmit}>
          <div style={{ margin: "2rem" }}>
            <FloatLabel>
              <InputText
                id="name"
                value={name}
                onChange={(e) => setName(e.target.value)}
              />
              <label htmlFor="name">Name</label>
            </FloatLabel>
          </div>

          <div style={{ margin: "2rem" }}>
            <FloatLabel>
              <InputText
                required={true}
                invalid={error}
                onBlur={validateEmail}
                id="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
              />
              <label htmlFor="email">Email</label>
            </FloatLabel>
            {error && (
              <small className="p-error c-small-error">
                Email is not valid!
              </small>
            )}
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
              fontWeight: "bolder",
            }}
          >
            Confirm
          </Button>
        </form>
      </Card>
    </div>
  );
}
