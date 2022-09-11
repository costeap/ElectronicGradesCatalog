import React, { useEffect, useState } from 'react';
import { useStyles } from '../Style.js';
import { Tabs, Tab, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Tooltip, Menu, MenuItem, Paper, Button, Dialog, DialogTitle, DialogContent, Typography, InputLabel, TextField } from '@material-ui/core';
import MenuIcon from '@material-ui/icons/Menu';
import CloseIcon from '@material-ui/icons/Close';
import axiosInstance from "../../../axios";
import Alert from '@material-ui/lab/Alert';
import * as SockJS from "sockjs-client";
import * as Stomp from "stompjs";


const ProfessorPage = ({ professorList }) => {

    const classes = useStyles();

    const [anchorEl, setAnchorEl] = useState(null);
    const [isCoursesOpen, setIsCoursesOpen] = useState(false);
    const [isEditModeOpen, setIsEditModeOpen] = useState(false);
    const [profDataset, setProfDataset] = useState()
    const [isAddModalOpen, setIsAddModalOpen] = useState()
    const [isModalEmailOpen, setIsModalEmailOpen] = useState()

    //pentru edit profesor
    const [numeProf, setNumeProf] = useState(0);
    const [cnpProf, setCnpProf] = useState(0);
    const [emailProf, setEmailProf] = useState(0);
    const [numarTelProf, setNumarTelProf] = useState(0);
    const [usernameProf, setUsernameProf] = useState(0);
    const [cursIdProf, setCursIdProf] = useState(0);

    //pentru adaugare profesor
    const [numeProfesor, setNumeProfesor] = useState(0);
    const [cnpProfesor, setCnpProfesor] = useState(0);
    const [emailProfesor, setEmailProfesor] = useState(0);
    const [numarTelProfesor, setNumarTelProfesor] = useState(0);
    const [usernameProfesor, setUsernameProfesor] = useState(0);
    const [parolaProfesor, setParolaProfesor] = useState(0);
    const [adminProfesor, setAdminProfesor] = useState(0);

    //pentru trimitere e-mail
    const [toEmail, setToEmail] = useState(0);
    const [subject, setSubject] = useState(0);
    const [body, setBody] = useState(0);

    const onMenuClick = (event, prof) => {
        event.stopPropagation();
        setProfDataset(prof)
        setAnchorEl(event.currentTarget);
    };

    const onMenuClose = () => {
        setAnchorEl(null);
    };

    const onViewCourses = () => {
        setIsCoursesOpen(true);
        onMenuClose()
    }

    const onCloseCourses = () => {
        setIsCoursesOpen(false);
    }

    const onEditProfessor = () => {
        setIsEditModeOpen(true);
        onMenuClose();
    }

    const onEditClose = () => {
        setIsEditModeOpen(false);
    }

    const onDeleteProfessor = () => {
        axiosInstance.get("/profesor/prof/" + Number(profDataset.id))
            .then(
                res => {

                    <Alert severity="succes">Professor deleted</Alert>
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    const onAddProfessor = () => {
        setIsAddModalOpen(true);
    }

    const onAddProfessorClose = () => {
        setIsAddModalOpen(false);
    }

    const onSendEmail = () => {
        setIsModalEmailOpen(true);
    }

    const onSendEmailClose = () => {
        setIsModalEmailOpen(false);
    }

    const editProfessor = () => {
        let credentilas = {
            cnp: cnpProf,
            nume: numeProf,
            email: emailProf,
            numarTel: numarTelProf,
            username: usernameProf,
            parola: profDataset.parola,
            admin: profDataset.admin,
            curs_id: cursIdProf
        }

        axiosInstance.put("/profesor/" + Number(profDataset.id), credentilas)
            .then(
                res => {
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    const addNewProfessor = () => {
        let credentilas = {
            cnp: cnpProfesor,
            nume: numeProfesor,
            email: emailProfesor,
            numarTel: numarTelProfesor,
            username: usernameProfesor,
            parola: parolaProfesor,
            admin: adminProfesor,
        }

        axiosInstance.post("/profesor", credentilas)
            .then(
                res => {
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    const sendEmail = () => {
        let credentilas = {
            toEmail: toEmail,
            body: body,
            subject: subject
        }
        console.log(toEmail);
        console.log(subject);
        console.log(body)
        axiosInstance.post("profesor/admin", credentilas)
            .then(
                res => {
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    const connect = () => {
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


    const generateCourses = () => {
        return (
            profDataset && (profDataset.listaCursuri.length != 0) ? (
                <TableContainer component={Paper}>
                    <Table className={classes.dialogTable} aria-label="customized table">
                        <TableHead className={classes.tableHead}>
                            <TableRow className={classes.tableRow}>
                                <TableCell className={classes.tableCell}>ID</TableCell>
                                <TableCell className={classes.tableCell}>Course Name</TableCell>
                                <TableCell className={classes.tableCell}>Year</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody className={classes.tableBody}>

                            {profDataset.listaCursuri.map((course) => (
                                <TableRow className={classes.tableRow} key={course.id}>
                                    <TableCell className={classes.tableCell} component="th" scope="row">
                                        {course.id}
                                    </TableCell>
                                    <TableCell className={classes.tableCell}> {course.numeCurs}</TableCell>
                                    <TableCell className={classes.tableCell}> {course.anDeStudiu}</TableCell>
                                </TableRow >
                            ))}

                        </TableBody>
                    </Table>

                </TableContainer>) : (<Typography>No courses</Typography>)
        )
    }


    return (<>
        <div className={classes.buttonContainer}>
            <Button className={classes.addButton} onClick={onAddProfessor}>Add new professor</Button>
            <Button className={classes.addButton} onClick={onSendEmail}>Send E-mail</Button>
        </div>
        <TableContainer component={Paper}>
            <Table className={classes.table} aria-label="customized table">
                <TableHead className={classes.tableHead}>
                    <TableRow className={classes.tableRow}>
                        <TableCell className={classes.tableCell}>ID</TableCell>
                        <TableCell className={classes.tableCell}>CNP</TableCell>
                        <TableCell className={classes.tableCell}>Name</TableCell>
                        <TableCell className={classes.tableCell}>E-mail</TableCell>
                        <TableCell className={classes.tableCell}>Phone</TableCell>
                        <TableCell className={classes.tableCell}>Username</TableCell>
                        <TableCell className={classes.buttonSpace}></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody className={classes.tableBody}>
                    {professorList.map((prof) => (<>
                        <TableRow className={classes.tableRow} key={prof.id}>
                            <TableCell className={classes.tableCell} component="th" scope="row">
                                {prof.id}
                            </TableCell>
                            <TableCell className={classes.tableCell}> {prof.cnp}</TableCell>
                            <TableCell className={classes.tableCell}> {prof.nume}</TableCell>
                            <TableCell className={classes.tableCell}> {prof.email}</TableCell>
                            <TableCell className={classes.tableCell}> {prof.numarTel}</TableCell>
                            <TableCell className={classes.tableCell}> {prof.username}</TableCell>
                            <TableCell className={classes.menuButton}>
                                <Tooltip title="Options" arrow placement="right">
                                    <Button onClick={(e) => onMenuClick(e, prof)}>
                                        <MenuIcon />
                                    </Button>

                                </Tooltip>
                            </TableCell>
                        </TableRow >


                    </>
                    ))}
                    <Menu anchorEl={anchorEl} open={Boolean(anchorEl)} onClose={onMenuClose}>
                        <MenuItem onClick={onDeleteProfessor}>Delete Professor</MenuItem>
                        <MenuItem onClick={onEditProfessor}>Edit Professor</MenuItem>
                        <MenuItem onClick={onViewCourses}>View courses</MenuItem>
                    </Menu>
                </TableBody>
            </Table>

        </TableContainer>

        <Dialog open={isCoursesOpen}>
            <DialogTitle style={{ borderBottom: "1px solid lightGrey" }}>
                <div className={classes.dialogTitle}>
                    <Typography variant="subtitle1">Courses</Typography>
                    <CloseIcon style={{ cursor: "pointer" }} onClick={onCloseCourses} />
                </div>

            </DialogTitle>
            <DialogContent >
                {generateCourses()}
            </DialogContent>
        </Dialog>

        <Dialog open={isModalEmailOpen}>
            <DialogTitle style={{ borderBottom: "1px solid lightGrey" }}>
                <div className={classes.dialogTitle}>
                    <Typography variant="subtitle1">E-mail</Typography>
                    <CloseIcon style={{ cursor: "pointer" }} onClick={onSendEmailClose} />
                </div>

            </DialogTitle>
            <DialogContent >
                <div className={classes.section}>
                    <TextField onChange={e => setToEmail(e.target.value)} label="To:" variant="filled" />
                </div>
                <div className={classes.section}>
                    <TextField onChange={e => setSubject(e.target.value)} label="Subject:" variant="filled" />
                </div>
                <div className={classes.section}>
                    <TextField onChange={e => setBody(e.target.value)} label="Body:" variant="filled" />
                </div>
                <div className={classes.container}>
                    <Button className={classes.button} onClick={sendEmail}>Send</Button>
                </div>
            </DialogContent>
        </Dialog>

        {profDataset &&
            <Dialog open={isEditModeOpen}>
                <DialogTitle style={{ borderBottom: "1px solid lightGrey" }}>
                    <div className={classes.dialogTitle}>
                        <Typography variant="subtitle1">Edit</Typography>
                        <CloseIcon style={{ cursor: "pointer" }} onClick={onEditClose} />
                    </div>

                </DialogTitle>
                <DialogContent>
                    <div className={classes.section}>
                        <TextField onChange={e => setNumeProf(e.target.value)} label="Name" variant="filled" />
                        <TextField onChange={e => setCnpProf(e.target.value)} label="CNP" variant="filled" />
                    </div>
                    <div className={classes.section}>
                        <TextField onChange={e => setEmailProf(e.target.value)} label="E-mail" variant="filled" />
                        <TextField onChange={e => setNumarTelProf(e.target.value)} label="Phone" variant="filled" />
                    </div>
                    <div className={classes.section}>
                        <TextField onChange={e => setUsernameProf(e.target.value)} label="Username" variant="filled" />
                        <TextField onChange={e => setCursIdProf(e.target.value)} label="Course ID" variant="filled" placeholder='Add new course:' />
                    </div>
                    <div className={classes.container}>
                        <Button className={classes.button} onClick={editProfessor}>Save</Button>
                    </div>
                </DialogContent>
            </Dialog>

        }
        <Dialog open={isAddModalOpen}>
            <DialogTitle style={{ borderBottom: "1px solid lightGrey" }}>
                <div className={classes.dialogTitle}>
                    <Typography variant="subtitle1">Add Professor</Typography>
                    <CloseIcon style={{ cursor: "pointer" }} onClick={onAddProfessorClose} />
                </div>

            </DialogTitle>
            <DialogContent >
                <div className={classes.section}>
                    <TextField onChange={e => setNumeProfesor(e.target.value)} label="Name" variant="filled" />
                    <TextField onChange={e => setCnpProfesor(e.target.value)} label="CNP" variant="filled" />
                </div>
                <div className={classes.section}>
                    <TextField onChange={e => setEmailProfesor(e.target.value)} label="E-mail" variant="filled" />
                    <TextField onChange={e => setNumarTelProfesor(e.target.value)} label="Phone" variant="filled" />
                </div>
                <div className={classes.section}>
                    <TextField onChange={e => setUsernameProfesor(e.target.value)} label="Username" variant="filled" />
                    <TextField onChange={e => setParolaProfesor(e.target.value)} label="Password" variant="filled" />
                </div>
                <div className={classes.section}>
                    <TextField onChange={e => setAdminProfesor(e.target.value)} label="Admin" variant="filled" />
                </div>
                <div className={classes.container}>
                    <Button className={classes.button} onClick={addNewProfessor}>Add</Button>
                </div>
            </DialogContent>
        </Dialog>
    </>
    )

}

export default ProfessorPage;