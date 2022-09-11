import React from "react";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Container from "@material-ui/core/Container";
import axiosInstance from "./axios";
import { Grid } from "@material-ui/core";
import history from './history';
import ReCAPTCHA from "react-google-recaptcha";
import * as SockJS from "sockjs-client";
import * as Stomp from "stompjs";




class Login extends React.Component {
    constructor() {
        super();
        this.state = {
            username: "",
            password: "",
        };
        this.state = {items: [], text: '', isVerified: false};
        this.handleOnChange = this.handleOnChange.bind(this);
        this.connect();
    }

    handleInput = event => {
        const { value, name } = event.target;
        this.setState({
            [name]: value
        });
        console.log(value);
    };

    onSubmitFun = event => {
        event.preventDefault();
        let credentilas = {
            username: this.state.username,
            parola: this.state.password,
        }
        console.log(credentilas)

        axiosInstance.post("/login", credentilas)
            .then(
                res => {
                    console.log(res.data);
                    const val = res.status;
                    const val1 = res.data.admin;
                    console.log(res.status);
                    console.log("Success");
                    localStorage.setItem("USER_ID", res.data.id);
                    localStorage.setItem("username", res.data.username)
                    localStorage.setItem("parola", res.data.parola)
                    console.log(res.data);
                    if (val == 200) {
                        localStorage.setItem("role", val1)
                        if (val1 == true) {
                            console.log("Salut")
                            window.location.assign("/admin");
                        }
                        else
                            window.location.assign("/profesor/" + res.data.nume + "/" + res.data.cnp);
                    }
                    else if (val == 201) {
                        window.location.assign("/student/" + res.data.id);
                    }
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    handleOnChange(value) {
        console.log("Captcha value:", value);
        this.setState({isVerified:true})
    }

    connect() {
        console.log("In Connect");
        const URL = "http://localhost:8080/socket";
        const websocket = new SockJS(URL);
        const stompClient = Stomp.over(websocket);
        stompClient.connect({}, frame => {
            console.log("Conectat la " + frame);
            stompClient.subscribe("/topic/socket/student", notification => {
                let message = notification.body;
                console.log(message);
                alert(message);

            })
        })
    }

    render() {
        const { match, location, history } = this.props;
        return (
            <Container maxWidth="sm">
                <div>
                    <Grid>
                        <form onSubmit={this.onSubmitFun}>
                            <TextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                id="username"
                                label="Username"
                                name="username"
                                autoComplete="string"
                                onChange={this.handleInput}
                                autoFocus
                            />
                            <TextField
                                variant="outlined"
                                margin="normal"
                                required
                                fullWidth
                                name="password"
                                label="Password"
                                type="password"
                                id="password"
                                onChange={this.handleInput}
                                autoComplete="current-password"
                            />
                            <ReCAPTCHA
                                sitekey="6LeIxAcTAAAAAJcZVRqyHh71UMIEGNQ_MXjiZKhI"
                                onChange={this.handleOnChange}
                            />
                            <Button disabled={!this.state.isVerified}
                                type="submit"
                                fullWidth
                                variant="contained"
                                color="primary"
                            >
                                Sign In
                            </Button>
                        </form>
                    </Grid>
                </div>
            </Container>
        );
    }

}

export default Login;