import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router';
import { Tabs, Tab, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Tooltip, Menu, MenuItem, Paper, Button, Dialog, Typography, DialogTitle, DialogContent } from '@material-ui/core';
import axiosInstance from "../../axios";
import Header from '../Header/Header';
import MenuIcon from '@material-ui/icons/Menu';
import { useStyles } from './Style.js';
import CloseIcon from '@material-ui/icons/Close';

import {
    ListItem,
    List,
    ListItemText,
    Avatar,
    ListItemIcon,
} from "@material-ui/core";
import EditIcon from '@mui/icons-material/Edit';
import * as SockJS from "sockjs-client";
import * as Stomp from "stompjs";


const Student = () => {
    const classes = useStyles();
    const [gradesList, setGradesList] = useState([])
    const [newCourses, setNewCourses] = useState([])
    const [anchorEl, setAnchorEl] = useState(null);
    const [isCoursesOpen, setIsCoursesOpen] = useState(false);
    const [isAddCoursesOpen, setIsAddCoursesOpen] = useState(false);
    const [courseDataset, setCourseDataset] = useState()


    const index = useParams();
    useEffect(() => getRepo(), [])
    const getRepo = () => {
        console.log(index);
        axiosInstance.get("catalog/student/" + index.index)
            .then((res) => {
                const gradesList = res.data;
                setGradesList(gradesList)
            })
            .catch((err) => {
                console.log(err);
            })
    }

    const onMenuClick = (event, course) => {
        setCourseDataset(course)
        setAnchorEl(event.currentTarget);
    };

    const onMenuClose = (e) => {
        setAnchorEl(null);
    };

    const onViewCourses = () => {
        setIsCoursesOpen(true);
        onMenuClose()
    }

    const onCloseCourses = () => {
        setIsCoursesOpen(false);
    }

    const onAddCourses = () => {
        setIsAddCoursesOpen(true);
        onMenuClose()
    }

    const onCloseAddCourses = () => {
        setIsAddCoursesOpen(false);
    }


    const getNewCourses = () => {
        axiosInstance.get("student/cursuri/" + localStorage.getItem("USER_ID"))
            .then(
                res => {
                    console.log(res.data)
                    const newCourses = res.data;
                    setNewCourses(newCourses)
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    const addCourseInCatalog = () => {
        let credentials = {
          student_id:localStorage.getItem("USER_ID"),
          curs_id:courseDataset.id,
          nota:0,
        }
        console.log(courseDataset.student_id)
        console.log(courseDataset.id)
        console.log(courseDataset.nota)
        axiosInstance.post("student/curs/catalog/"+credentials.student_id+"/"+credentials.curs_id+"/"+credentials.nota)
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
    return (

        <div>

            <Header userType="Student" />

            <div className={classes.buttonContainer}>
                <Button className={classes.addButton} onClick={onViewCourses}>Show new courses</Button>
            </div>

            <TableContainer component={Paper}>
                <Table className={classes.table} aria-label="customized table">
                    <TableHead className={classes.tableHead}>
                        <TableRow className={classes.tableRow}>
                            <TableCell className={classes.tableCell}>Student ID</TableCell>
                            <TableCell className={classes.tableCell}>Name</TableCell>
                            <TableCell className={classes.tableCell}>Curs ID</TableCell>
                            <TableCell className={classes.tableCell}>Course Name</TableCell>
                            <TableCell className={classes.tableCell}>Grade</TableCell>
                            <TableCell className={classes.buttonSpace}></TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody className={classes.tableBody}>
                        {gradesList.map((stud) => (<>
                            <TableRow className={classes.tableRow} key={stud.id}>
                                <TableCell className={classes.tableCell} component="th" scope="row">
                                    {stud.student_id}
                                </TableCell>
                                <TableCell className={classes.tableCell}> {stud.numeStudent}</TableCell>
                                <TableCell className={classes.tableCell}> {stud.curs_id}</TableCell>
                                <TableCell className={classes.tableCell}> {stud.numeCurs}</TableCell>
                                <TableCell className={classes.tableCell}> {stud.nota}</TableCell>
                                <TableCell className={classes.menuButton}>
                                </TableCell>
                            </TableRow >
                        </>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>

            <Dialog open={isCoursesOpen}>
                <DialogTitle style={{ borderBottom: "1px solid lightGrey" }}>
                    <div className={classes.dialogTitle}>
                        <Typography variant="subtitle1"></Typography>
                        <Button className={classes.addButton} onClick={getNewCourses}>Show</Button>
                        <CloseIcon style={{ cursor: "pointer" }} onClick={onCloseCourses} />
                    </div>

                </DialogTitle>
                <DialogContent>
                    <TableContainer component={Paper}>
                        <Table className={classes.dialogTable} aria-label="customized table">
                            <TableHead className={classes.tableHead}>
                                <TableRow className={classes.tableRow}>
                                    <TableCell className={classes.tableCell}>ID</TableCell>
                                    <TableCell className={classes.tableCell}>Course Name</TableCell>
                                    <TableCell className={classes.tableCell}>Year</TableCell>
                                    <TableCell className={classes.buttonSpace}></TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody className={classes.tableBody}>

                                {newCourses.map((course) => (
                                    <TableRow className={classes.tableRow} key={course.id}>
                                        <TableCell className={classes.tableCell} component="th" scope="row">
                                            {course.id}
                                        </TableCell>
                                        <TableCell className={classes.tableCell}> {course.numeCurs}</TableCell>
                                        <TableCell className={classes.tableCell}> {course.anDeStudiu}</TableCell>
                                        <TableCell className={classes.menuButton}>
                                            <Tooltip title="Options" arrow placement="right">
                                                <Button onClick={(e) => onMenuClick(e, course)}>
                                                    <MenuIcon />
                                                </Button>

                                            </Tooltip>
                                        </TableCell>
                                    </TableRow >
                                ))}

                                <Menu anchorEl={anchorEl} open={Boolean(anchorEl)} onClose={onMenuClose}>
                                    <MenuItem onClick={addCourseInCatalog}>Add course</MenuItem>
                                </Menu>

                            </TableBody>
                        </Table>

                    </TableContainer>
                </DialogContent>
            </Dialog>

        </div>
    )

}


export default Student;