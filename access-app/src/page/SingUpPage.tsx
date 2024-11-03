import React, { useRef } from "react";
import { useNavigate, useLocation, Link } from "react-router-dom";
import { Card } from "primereact/card";
import { InputText } from "primereact/inputtext";
import { FloatLabel } from "primereact/floatlabel";
import { Password } from "primereact/password";
import { Button } from "primereact/button";
import { useAuth } from "../auth/auth-context";
import { Toast } from "primereact/toast";

export default function SingUpPage() {
  const [email, setEmail] = React.useState<string>("");
  const [name, setName] = React.useState<string>("");
  const [password, setPassword] = React.useState<string>("");
  const [error, setError] = React.useState<any>({
    email:false,
    name: false,
    password: false
  });
  const toast = useRef<Toast>(null!);

  const auth = useAuth();

  let location = useLocation();

  let navigate = useNavigate();

  function validateEmail() {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    error.email = re.test(email) == false;
    setError(error);
  }

  function validateName() {
    error.name = name.length < 3;
    setError(error);
  }

  function validatePassword() {
    error.password = password.length < 8;
    setError(error);
  }


  async function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
    event.preventDefault();
    try {
      await auth.register(name, email, password);
      navigate("/", { replace: true });
    } catch (errorMessage) {
      console.log(errorMessage);
      toast.current.show({
        severity: "error",
        summary: "Register Error",
        detail: errorMessage.toString(),
        life: 8000,
      });      
    }
  }

  return (
    <>
      <h1 style={{ textAlign: "center" }}>ACCESS MANAGEMENT</h1>{" "}
      <div className="c-card-container">
        <Card className="" title="">
          <form onSubmit={handleSubmit}>
            <div style={{ margin: "2rem" }}>
              <FloatLabel>
                <InputText
                  id="name"
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                  onBlur={validateName}
                  invalid={error.name}
                />
                <label htmlFor="name">Name</label>
                {error.name && (
                <small className="p-error c-small-error">
                  Name has to be at least 3 characters
                </small>
              )}
              </FloatLabel>
            </div>

            <div style={{ margin: "2rem" }}>
              <FloatLabel>
                <InputText
                  required={true}
                  invalid={error.email}
                  onBlur={validateEmail}
                  id="email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
                <label htmlFor="email">Email</label>
              </FloatLabel>
              {error.email && (
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
                  required={true}
                  feedback={true}
                  onBlur={validatePassword}
                  invalid={error.password}
                  onChange={(e) => setPassword(e.target.value)}
                  maxLength={16}
                  minLength={8}
                />
                <label htmlFor="password">Password</label>
              </FloatLabel>
              {error.password && (
                <small className="p-error c-small-error">
                  Password is not valid!
                </small>
              )}
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
          <hr />
          <Button
            type="submit"
            style={{
              width: "200px",
              margin: "2rem",
              backgroundColor: "darksalmon",
              borderColor: "darksalmon",
              fontWeight: "bolder",
            }}
            onClick={() => navigate("/login", { replace: true })}
          >
            Login
          </Button>
        </Card>
        </div>
      <footer style={{ textAlign: "center" }}>João Vitor Cardoso [2024]</footer>
      <Toast ref={toast} />
    </>
  );
}
