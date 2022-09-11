import React, { useEffect, useState } from 'react';
import { useStyles } from '../Style.js';
import { Tabs, Tab, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Tooltip, Menu, MenuItem, Paper, Button, Dialog, DialogTitle, DialogContent, Typography, TextField } from '@material-ui/core';
import MenuIcon from '@material-ui/icons/Menu';
import CloseIcon from '@material-ui/icons/Close';
import axiosInstance from "../../../axios";
import Alert from '@material-ui/lab/Alert';

const CoursePage = ({ courseList }) => {

    const classes = useStyles();

    const [anchorEl, setAnchorEl] = useState(null);
    const [cursDataset, setCursDataset] = useState();
    const [isProfessorsOpen, setIsProfessorsOpen] = useState(false);
    const [isAddModalOpen, setIsAddModalOpen] = useState();
    const [isEditModeOpen, setIsEditModeOpen] = useState(false);
    const [isDownloadGradesOpen, setIsDownloadGradesOpen] = useState(false);
    const [isDownloadGradesXmlOpen, setIsDownloadGradesXmlOpen] = useState(false);

    //pentru adaugare curs
    const [numeCurs, setNumeCurs] = useState(0);
    const [anDeStudiu, setAnDeStudiu] = useState(0);

    //pentru edit curs
    const [numeCursEdit, setNumeCursEdit] = useState(0);
    const [anDeStudiuEdit, setAnDeStudiuEdit] = useState(0);
    const [profIdCurs, setProfIdCurs] = useState(0);

    //descarcare note
    const [numeCursD, setNumeCursD] = useState(0);

    //descarcare note xml
    const [numeCursXml, setNumeCursXml] = useState(0);

    const onMenuClick = (event, curs) => {
        event.stopPropagation();
        setCursDataset(curs)
        setAnchorEl(event.currentTarget);
    };

    const onMenuClose = () => {
        setAnchorEl(null);
    };

    const onViewProfessors = () => {
        setIsProfessorsOpen(true);
        onMenuClose()
    }

    const onCloseProfessors = () => {
        setIsProfessorsOpen(false);
    }

    const onAddCourse = () => {
        setIsAddModalOpen(true);
    }

    const onAddCourseClose = () => {
        setIsAddModalOpen(false);
    }

    const onEditCourse = () => {
        setIsEditModeOpen(true);
        onMenuClose();
    }

    const onEditClose = () => {
        setIsEditModeOpen(false);
    }

    const onDownloadGrades = () => {
        setIsDownloadGradesOpen(true);
    }

    const onDownloadGradesClose = () => {
        setIsDownloadGradesOpen(false);
    }

    const onDownloadGradesXml = () => {
        setIsDownloadGradesXmlOpen(true);
    }

    const onDownloadGradesXmlClose = () => {
        setIsDownloadGradesXmlOpen(false);
    }

    const onDeleteCourse = () => {
        axiosInstance.get("/curs/curs/" + Number(cursDataset.id))
            .then(
                res => {

                    <Alert severity="succes">Course deleted</Alert>
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    const addNewCourse = () => {
        let credentilas = {
            numeCurs: numeCurs,
            anDeStudiu: anDeStudiu,
        }

        axiosInstance.post("/curs", credentilas)
            .then(
                res => {
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    const editCourse = () => {
        let credentilas = {
            numeCurs: numeCursEdit,
            anDeStudiu: anDeStudiuEdit,
            profesor_id: profIdCurs,
        }

        axiosInstance.put("/curs/" + Number(cursDataset.id), credentilas)
            .then(
                res => {
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    const downloadGrades = () => {


        axiosInstance.get("/catalog/" + numeCursD)
            .then(
                res => {
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    const downloadGradesXml = () => {


        axiosInstance.get("/catalog/xml/" + numeCursXml)
            .then(
                res => {
                }
            )
            .catch(error => {
                console.log(error)
            })
    }
    const generateProfessors = () => {
        return (
            cursDataset && (cursDataset.listaProfesori.length != 0) ? (
                <TableContainer component={Paper}>
                    <Table className={classes.dialogTable} aria-label="customized table">
                        <TableHead className={classes.tableHead}>
                            <TableRow className={classes.tableRow}>
                                <TableCell className={classes.tableCellDialog}>ID</TableCell>
                                <TableCell className={classes.tableCellDialog}>CNP</TableCell>
                                <TableCell className={classes.tableCellDialog}>Name</TableCell>
                                <TableCell className={classes.tableCellDialog}>Email</TableCell>
                                <TableCell className={classes.tableCellDialog}>Phone</TableCell>
                                <TableCell className={classes.tableCellDialog}>Username</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody className={classes.tableBody}>

                            {cursDataset.listaProfesori.map((professor) => (
                                <TableRow className={classes.tableRow} key={professor.id}>
                                    <TableCell className={classes.tableCellDialog} component="th" scope="row">
                                        {professor.id}
                                    </TableCell>
                                    <TableCell className={classes.tableCellDialog}> {professor.cnp}</TableCell>
                                    <TableCell className={classes.tableCellDialog}> {professor.nume}</TableCell>
                                    <TableCell className={classes.tableCellDialog}> {professor.email}</TableCell>
                                    <TableCell className={classes.tableCellDialog}> {professor.numarTel}</TableCell>
                                    <TableCell className={classes.tableCellDialog}> {professor.username}</TableCell>
                                </TableRow >
                            ))}

                        </TableBody>
                    </Table>

                </TableContainer>) : (<Typography>No professors</Typography>)
        )
    }



    return (<>
        <div className={classes.buttonContainer}>
            <Button className={classes.addButton} onClick={onAddCourse}>Add new course</Button>
            <Button className={classes.addButton} onClick={onDownloadGrades}>Download grades</Button>
            <Button className={classes.addButton} onClick={onDownloadGradesXml}>Download grades XML</Button>
        </div>
        <TableContainer component={Paper}>
            <Table className={classes.table} aria-label="customized table">
                <TableHead className={classes.tableHead}>
                    <TableRow className={classes.tableRow}>
                        <TableCell className={classes.tableCell}>ID</TableCell>
                        <TableCell className={classes.tableCell}>Course Name</TableCell>
                        <TableCell className={classes.tableCell}>Year</TableCell>
                        <TableCell className={classes.buttonSpace}></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody className={classes.tableBody}>
                    {courseList.map((course) => (<>
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
                    </>
                    ))}
                    <Menu anchorEl={anchorEl} open={Boolean(anchorEl)} onClose={onMenuClose}>
                        <MenuItem onClick={onDeleteCourse} >Delete Course</MenuItem>
                        <MenuItem onClick={onEditCourse}>Edit Course</MenuItem>
                        <MenuItem onClick={onViewProfessors} >View Professors</MenuItem>
                    </Menu>
                </TableBody>
            </Table>
        </TableContainer>

        <Dialog open={isProfessorsOpen}>
            <DialogTitle style={{ borderBottom: "1px solid lightGrey" }}>
                <div className={classes.dialogTitle}>
                    <Typography variant="subtitle1">Professors</Typography>
                    <CloseIcon style={{ cursor: "pointer" }} onClick={onCloseProfessors} />
                </div>

            </DialogTitle>
            <DialogContent >
                {generateProfessors()}
            </DialogContent>
        </Dialog>

        <Dialog open={isAddModalOpen}>
            <DialogTitle style={{ borderBottom: "1px solid lightGrey" }}>
                <div className={classes.dialogTitle}>
                    <Typography variant="subtitle1">Add Course</Typography>
                    <CloseIcon style={{ cursor: "pointer" }} onClick={onAddCourseClose} />
                </div>

            </DialogTitle>
            <DialogContent >
                <div className={classes.section}>
                    <TextField onChange={e => setNumeCurs(e.target.value)} label="Name" variant="filled" />
                    <TextField onChange={e => setAnDeStudiu(e.target.value)} label="Year" variant="filled" />
                </div>
                <div className={classes.container}>
                    <Button className={classes.button} onClick={addNewCourse}>Add</Button>
                </div>
            </DialogContent>
        </Dialog>

        {cursDataset &&
            <Dialog open={isEditModeOpen}>
                <DialogTitle style={{ borderBottom: "1px solid lightGrey" }}>
                    <div className={classes.dialogTitle}>
                        <Typography variant="subtitle1">Edit</Typography>
                        <CloseIcon style={{ cursor: "pointer" }} onClick={onEditClose} />
                    </div>

                </DialogTitle>
                <DialogContent>
                    <div className={classes.section}>
                        <TextField onChange={e => setNumeCursEdit(e.target.value)} label="Name" variant="filled" />
                        <TextField onChange={e => setAnDeStudiuEdit(e.target.value)} label="Year" variant="filled" />
                    </div>
                    <div className={classes.section}>
                        <TextField onChange={e => setProfIdCurs(e.target.value)} label="Professor ID" variant="filled" placeholder='Add new professsor:' />
                    </div>
                    <div className={classes.container}>
                        <Button className={classes.button} onClick={editCourse}>Save</Button>
                    </div>
                </DialogContent>
            </Dialog>
        }

        <Dialog open={isDownloadGradesOpen}>
            <DialogTitle style={{ borderBottom: "1px solid lightGrey" }}>
                <div className={classes.dialogTitle}>
                    <Typography variant="subtitle1">Download grades</Typography>
                    <CloseIcon style={{ cursor: "pointer" }} onClick={onDownloadGradesClose} />
                </div>

            </DialogTitle>
            <DialogContent >
                <div className={classes.section}>
                    <TextField onChange={e => setNumeCursD(e.target.value)} label="Course Name" variant="filled" />
                </div>
                <div className={classes.container}>
                    <Button className={classes.button} onClick={downloadGrades}>Send</Button>
                </div>
            </DialogContent>
        </Dialog>

        <Dialog open={isDownloadGradesXmlOpen}>
            <DialogTitle style={{ borderBottom: "1px solid lightGrey" }}>
                <div className={classes.dialogTitle}>
                    <Typography variant="subtitle1">Download grades</Typography>
                    <CloseIcon style={{ cursor: "pointer" }} onClick={onDownloadGradesXmlClose} />
                </div>

            </DialogTitle>
            <DialogContent >
                <div className={classes.section}>
                    <TextField onChange={e => setNumeCursXml(e.target.value)} label="Course Name" variant="filled" />
                </div>
                <div className={classes.container}>
                    <Button className={classes.button} onClick={downloadGradesXml}>Send</Button>
                </div>
            </DialogContent>
        </Dialog>
    </>)

}

export default CoursePage;