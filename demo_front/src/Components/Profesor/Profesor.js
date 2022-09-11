import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router';
import { Tabs, Tab, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Tooltip, Menu, MenuItem, Paper, Button, TextField, InputLabel, Typography, Dialog, DialogTitle, DialogContent } from '@material-ui/core';
import axiosInstance from "../../axios";
import Header from '../Header/Header';
import MenuIcon from '@material-ui/icons/Menu';
import { useStyles } from './Style.js';
import { makeStyles } from '@material-ui/core/styles';
import { Label } from '@mui/icons-material';
import { autocompleteClasses } from '@mui/material';
import CloseIcon from '@material-ui/icons/Close';

const Profesor = () => {
    const [anchorEl, setAnchorEl] = useState(null);
    const [studId, setStudId] = useState(0);
    const [courseId, setCourseId] = useState(0);
    const [grade, setGrade] = useState(0);
    const [isEditModeOpen, setIsEditModeOpen] = useState(false);

    //pentru edit nota
    const [studentId, setStudentId] = useState(0);
    const [cursId, setCursId] = useState(0);
    const [nota, setNota] = useState(0);

    const nume = useParams()
    const cnp = useParams()
    console.log(nume)
    console.log(cnp)
    const addGrade = () => {
        let credentilas = {
            student_id: studId,
            curs_id: courseId,
            nota: grade,
            numeProfesor: nume.nume,
            cnpProfesor: cnp.cnp,
        }
        console.log(studId);
        console.log(courseId);
        console.log(grade);

        axiosInstance.post("/catalog", credentilas)
            .then(
                res => {
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    const editGrade = () => {
        let credentilas = {
            student_id: studentId,
            curs_id: cursId,
            nota: nota,
            numeProfesor: nume.nume,
            cnpProfesor: cnp.cnp,
        }
        console.log(studentId);
        console.log(cursId);
        console.log(nota);
        console.log(nume.nume);
        console.log(cnp.cnp);

        axiosInstance.post("/catalog/student/curs", credentilas)
            .then(
                res => {
                }
            )
            .catch(error => {
                console.log(error)
            })
    }


    const useStyles = makeStyles((theme) => ({
        root: {
            padding: 20,
            margin: "auto",
            boxShadow: "0 2px 12px 1px rgba(60, 62, 66, 0.1)",
            width: "fit-content",
            marginTop: 50

        },
        root1: {
            display: "flex",
            alignItems: "center",
            justifyContent: "space-evenly",
            height: 40,
            padding: "10px 20px",
            margin: 8,
        },
        button: {
            backgroundColor: "#FF8E53"
        },
        container: {
            margin: "auto",
            maxWidth: "fit-content",
            marginTop: 8
        },
        title: {
            margin: "auto",
            width: "fit-content"
        },
        tabs: {
            boxShadow: "5px 0 5px rgb(0 0 0 / 20%)",
        },
        table: {
            margin: "40px auto",
            maxWidth: "70% !important",
        },
        tableUsersActivity: {
            margin: "60px auto",
            maxWidth: "120% !important",
        },
        tableHead: {
            backgroundColor: "#FF8E53"
        },
        tableBody: {
            fontSize: 14
        },
        tableRow: {
            "&:nth-child(even)": {
                backgroundColor: "lightGrey"
            }
        },
        tableCell: {
            maxWidth: 50
        },
        tableCellDialog: {
            maxWidth: 500
        },
        dialogTitle: {
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            minWidth: 500
        },
        dialogTable: {
            margin: "0 auto",
        },
        section: {
            display: "flex",
            justifyContent: "space-around",
            alignItems: "center",
            marginBottom: 10
        },
        addButton: {
            backgroundColor: "#FF8E53"
        },
        buttonContainer: {
            margin: "auto",
            maxWidth: "fit-content",
            marginTop: 20
        },
        container: {
            margin: "auto",
            maxWidth: "fit-content",
            marginTop: 8
        }

    }));

    const classes = useStyles();

    const onMenuClose = () => {
        setAnchorEl(null);
    };

    const onEditGrade = () => {
        setIsEditModeOpen(true);
        onMenuClose();
    }

    const onEditClose = () => {
        setIsEditModeOpen(false);
    }

    return (
        <div>

            <Header userType="Profesor" />
            <div className={classes.root}>
                <div className={classes.title}>
                    <Typography variant="h5">Add Grade</Typography>
                </div>
                <div className={classes.root1}>
                    <TextField onChange={e => setStudId(e.target.value)} label="Student ID" variant="filled" />
                </div>
                <div className={classes.root1}>
                    <TextField onChange={e => setCourseId(e.target.value)} label="Course ID" variant="filled" />
                </div>
                <div className={classes.root1}>
                    <TextField onChange={e => setGrade(e.target.value)} label="Grade" variant="filled" />
                </div>
                <div className={classes.container}>
                    <Button className={classes.button} onClick={addGrade}>Add</Button>
                    <Button className={classes.button} onClick={onEditGrade}>Edit</Button>
                </div>
            </div>

            <Dialog open={isEditModeOpen}>
                <DialogTitle style={{ borderBottom: "1px solid lightGrey" }}>
                    <div className={classes.dialogTitle}>
                        <Typography variant="subtitle1">Edit</Typography>
                        <CloseIcon style={{ cursor: "pointer" }} onClick={onEditClose} />
                    </div>

                </DialogTitle>
                <DialogContent>
                    <div className={classes.section}>
                        <TextField onChange={e => setStudentId(e.target.value)} label="Student ID" variant="filled" />
                        <TextField onChange={e => setCursId(e.target.value)} label="Curs ID" variant="filled" />
                    </div>
                    <div className={classes.section}>
                        <TextField onChange={e => setNota(e.target.value)} label="Grade" variant="filled" />
                    </div>
                    <div className={classes.section}>
                        <Button className={classes.button} onClick={editGrade}>Edit</Button>
                    </div>
                    <div className={classes.container}>

                    </div>
                </DialogContent>
            </Dialog>


        </div>
    )
}


export default Profesor;