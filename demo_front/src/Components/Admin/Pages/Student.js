import React, { useEffect, useState } from 'react';
import { useStyles } from '../Style.js';
import { Tabs, Tab, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Tooltip, Menu, MenuItem, Paper, Button, Dialog, DialogTitle, DialogContent, TextField, Typography, Input } from '@material-ui/core';
import MenuIcon from '@material-ui/icons/Menu';
import CloseIcon from '@material-ui/icons/Close';
import axiosInstance from "../../../axios";
import Alert from '@material-ui/lab/Alert';
import WebSocketListenerInstance from '../../Ws/WebSocketListener.js';
import * as SockJS from "sockjs-client";
import * as Stomp from "stompjs";


const StudentPage = ({ studentList }) => {

    
    const classes = useStyles();

    const [anchorEl, setAnchorEl] = useState(null);
    const [isAddModalOpen, setIsAddModalOpen] = useState();
    const [isEditModeOpen, setIsEditModeOpen] = useState(false);
    const [studentDataset, setStudentDataset] = useState()

    //pentru adaugare student
    const [numeStudent, setNumeStudent] = useState(0);
    const [cnpStudent, setCnpStudent] = useState(0);
    const [emailStudent, setEmailStudent] = useState(0);
    const [anStudiu, setAnStudiu] = useState(0);
    const [usernameStudent, setUsernameStudent] = useState(0);
    const [parolaStudent, setParolaStudent] = useState(0);

    //pentru edit student
    const [numeStudentEdit, setNumeStudentEdit] = useState(0);
    const [cnpStudentEdit, setCnpStudentEdit] = useState(0);
    const [emailStudentEdit, setEmailStudentEdit] = useState(0);
    const [anStudiuEdit, setAnStudiuEdit] = useState(0);
    const [usernameStudentEdit, setUsernameStudentEdit] = useState(0);

    const onMenuClick = (event, student) => {
        event.stopPropagation();
        setStudentDataset(student);
        setAnchorEl(event.currentTarget);
    };

    const onMenuClose = (e) => {
        e.stopPropagation();
        setAnchorEl(null);
    };

    const onAddStudent = () => {
        setIsAddModalOpen(true);
    }

    const onAddStudentClose = () => {
        setIsAddModalOpen(false);
    }

    const onEditStudent = () => {
        setIsEditModeOpen(true);
        onMenuClose();
    }

    const onEditClose = () => {
        setIsEditModeOpen(false);
    }

    const addNewStudent = () => {
        let credentilas = {
            cnp: formValues.cnp,
            nume: formValues.name,
            email: formValues.email,
            anStudiu: formValues.year,
            username: formValues.username,
            parola: formValues.password,
        }

        console.log(formValues.name)
        console.log(formValues.cnp)
        console.log(formValues.email)
        console.log(formValues.year)
        console.log(formValues.username)
        console.log(formValues.password)

        axiosInstance.post("/student", credentilas)
            .then(
                res => {
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    const editStudent = () => {
        let credentilas = {
            cnp: cnpStudentEdit,
            nume: numeStudentEdit,
            email: emailStudentEdit,
            anStudiu: anStudiuEdit,
            username: usernameStudentEdit,
            parola: studentDataset.parola,
        }

        console.log(cnpStudentEdit);

        axiosInstance.put("/student/" + Number(studentDataset.id), credentilas)
            .then(
                res => {
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    const onDeleteStudent = () => {
        axiosInstance.get("/student/stud/" + Number(studentDataset.id))
            .then(
                res => {

                    <Alert severity="succes">Student deleted</Alert>
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    const initialValues = { name: "", cnp: "", email: "", year: "", username: "", password: "" };
    const [formValues, setFormValues] = useState(initialValues);
    const [formErrors, setFormErrors] = useState({});
    const [isSubmit, setIsSubmit] = useState(false);

    const hStyle = { color: 'red' };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormValues({ ...formValues, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setFormErrors(validate(formValues));
        setIsSubmit(true);
        console.log(Object.keys(formErrors).length)
        if (Object.keys(formErrors).length === 0)
        {
            addNewStudent();
        }
    };

    useEffect(() => {
        console.log(formErrors);
        if (Object.keys(formErrors).length === 0 && isSubmit) {
            console.log(formValues);
        }
    }, [formErrors]);

    const validate = (values) => {
        const errors = {}
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;

        const uppercaseRegExp   = /(?=.*?[A-Z])/;
        const lowercaseRegExp   = /(?=.*?[a-z])/;
        const digitsRegExp      = /(?=.*?[0-9])/;

        if (!values.name) {
            errors.name = "Name is required!";
        } else if (values.name.length < 5) {
            errors.name = "Name must be more than 5 characters!";
        } else if (values.name.length > 200) {
            errors.name = "Name cannot exceed more than 200 characters!";
        }

        if (!values.cnp) {
            errors.cnp = "CNP is required!";
        }else if (values.cnp.length != 13) {
            errors.cnp = "CNP must have 13 characters!";
        }

        if (!values.year) {
            errors.year = "Year is required!";
        }

        if (!values.username) {
            errors.username = "Username is required!";
        }

        if (!values.email) {
            errors.email = "Email is required!";
        } else if (!regex.test(values.email)) {
            errors.email = "This is not a valid email format!";
        }

        if (!values.password) {
            errors.password = "Password is required!";
        } else if (values.password.length < 5) {
            errors.password = "Password must be more than 5 characters!";
        } else if (values.password.length > 100) {
            errors.password = "Password cannot exceed more than 100 characters!";
        } else if (!uppercaseRegExp.test(values.password))
        {
            errors.password = "Password must have at least one uppercase character!";
        }else if (!lowercaseRegExp.test(values.password))
        {
            errors.password = "Password must have at least one lowercase character!";
        }else if (!digitsRegExp.test(values.password))
        {
            errors.password = "Password must have at least one digit/number!";
        }

        return errors;
    }

    const onClick = () => {
        handleSubmit();
        addNewStudent();
    }
   
    return (<>
        <div className={classes.buttonContainer}>
            <Button className={classes.addButton} onClick={onAddStudent}>Add new student</Button>
        </div>
        <TableContainer component={Paper}>
            <Table className={classes.table} aria-label="customized table">
                <TableHead className={classes.tableHead}>
                    <TableRow className={classes.tableRow}>
                        <TableCell className={classes.tableCell}>ID</TableCell>
                        <TableCell className={classes.tableCell}>CNP</TableCell>
                        <TableCell className={classes.tableCell}>Name</TableCell>
                        <TableCell className={classes.tableCell}>E-mail</TableCell>
                        <TableCell className={classes.tableCell}>Year</TableCell>
                        <TableCell className={classes.tableCell}>Username</TableCell>
                        <TableCell className={classes.buttonSpace}></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody className={classes.tableBody}>
                    {studentList.map((stud) => (<>
                        <TableRow className={classes.tableRow} key={stud.id}>
                            <TableCell className={classes.tableCell} component="th" scope="row">
                                {stud.id}
                            </TableCell>
                            <TableCell className={classes.tableCell}> {stud.cnp}</TableCell>
                            <TableCell className={classes.tableCell}> {stud.nume}</TableCell>
                            <TableCell className={classes.tableCell}> {stud.email}</TableCell>
                            <TableCell className={classes.tableCell}> {stud.anStudiu}</TableCell>
                            <TableCell className={classes.tableCell}> {stud.username}</TableCell>
                            <TableCell className={classes.menuButton}>
                                <Tooltip title="Options" arrow placement="right">
                                    <Button onClick={(e) => onMenuClick(e, stud)}>
                                        <MenuIcon />
                                    </Button>

                                </Tooltip>
                            </TableCell>
                        </TableRow >

                        <Menu anchorEl={anchorEl} open={Boolean(anchorEl)} onClose={onMenuClose}>
                            <MenuItem onClick={onDeleteStudent}>Delete Student</MenuItem>
                            <MenuItem onClick={onEditStudent} >Edit Student</MenuItem>
                        </Menu>
                    </>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>

        <Dialog open={isAddModalOpen}>
            <DialogTitle style={{ borderBottom: "1px solid lightGrey" }}>
                <div className={classes.dialogTitle}>
                    <Typography variant="subtitle1">Add Student</Typography>
                    <CloseIcon style={{ cursor: "pointer" }} onClick={onAddStudentClose} />
                </div>

            </DialogTitle>
            <DialogContent >
                <form onSubmit={handleSubmit}>
                    <p style={hStyle}>{formErrors.name}</p>
                    <div className={classes.section}>
                        <TextField name="name" value={formValues.name} onChange={handleChange} label="Name" variant="filled" />
                    </div>
                    <p style={hStyle}>{formErrors.cnp}</p>
                    <div className={classes.section}>
                        <TextField name="cnp" value={formValues.cnp} onChange={handleChange} label="CNP" variant="filled" />
                    </div>
                    <p style={hStyle}>{formErrors.email}</p>
                    <div className={classes.section}>
                        <TextField name="email" value={formValues.email} onChange={handleChange} label="E-mail" variant="filled" />
                    </div>
                    <p style={hStyle}>{formErrors.year}</p>
                    <div className={classes.section}>
                        <TextField name="year" value={formValues.year} onChange={handleChange} label="Year" variant="filled" />
                    </div>
                    <p style={hStyle}>{formErrors.username}</p>
                    <div className={classes.section}>
                        <TextField name="username" value={formValues.username} onChange={handleChange} label="Username" variant="filled" />
                    </div>
                    <p style={hStyle}>{formErrors.password}</p>
                    <div className={classes.section}>
                        <TextField name="password" value={formValues.password} onChange={handleChange} label="Password" variant="filled" />
                    </div>

                    <div className={classes.container}>
                        <Button className={classes.button} onClick={handleSubmit}>Add</Button>
                    </div>
                </form>
            </DialogContent>
        </Dialog>

        {studentDataset &&
            <Dialog open={isEditModeOpen}>
                <DialogTitle style={{ borderBottom: "1px solid lightGrey" }}>
                    <div className={classes.dialogTitle}>
                        <Typography variant="subtitle1">Edit</Typography>
                        <CloseIcon style={{ cursor: "pointer" }} onClick={onEditClose} />
                    </div>

                </DialogTitle>
                <DialogContent>
                    <div className={classes.section}>
                        <TextField onChange={e => setNumeStudentEdit(e.target.value)} label="Name" variant="filled" />
                        <TextField onChange={e => setCnpStudentEdit(e.target.value)} label="CNP" variant="filled" />
                    </div>
                    <div className={classes.section}>
                        <TextField onChange={e => setEmailStudentEdit(e.target.value)} label="E-mail" variant="filled" />
                        <TextField onChange={e => setAnStudiuEdit(e.target.value)} label="Year" variant="filled" />
                    </div>
                    <div className={classes.section}>
                        <TextField onChange={e => setUsernameStudentEdit(e.target.value)} label="Username" variant="filled" />
                    </div>
                    <div className={classes.container}>
                        <Button className={classes.button} onClick={editStudent}>Save</Button>
                    </div>
                </DialogContent>
            </Dialog>

        }
    </>
    )

}

export default StudentPage;