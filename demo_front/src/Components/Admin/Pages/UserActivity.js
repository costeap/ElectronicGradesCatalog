import React, { useEffect, useState } from 'react';
import { useStyles } from '../Style.js';
import { Tabs, Tab, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Tooltip, Menu, MenuItem, Paper, Button, Dialog, DialogTitle, DialogContent, TextField, Typography } from '@material-ui/core';
import MenuIcon from '@material-ui/icons/Menu';
import CloseIcon from '@material-ui/icons/Close';
import axiosInstance from "../../../axios";
import Alert from '@material-ui/lab/Alert';

const UserActivityPage = ({ usersList }) => {

    const classes = useStyles();

    const [anchorEl, setAnchorEl] = useState(null);

    const [usersDataset, setUsersDataset] = useState();
    const [isSearchModalOpen, setIsSearchModalOpen] = useState();
    const [isConnectedUsersModalOpen, setIsConnectedUsersModalOpen] = useState();
    const [username, setUsername] = useState(0);
    const [usersConnected, setUsersConnected] = useState([])
    const [userActivity, setUserActivity] = useState([])

    const [trigger, setTrigger] = useState(0);

    const onMenuClick = (event, user) => {
        event.stopPropagation();
        setUsersDataset(user);
        setAnchorEl(event.currentTarget);
    };

    const onMenuClose = (e) => {
        e.stopPropagation();
        setAnchorEl(null);
    };


    const onSearchUser = () => {
        setIsSearchModalOpen(true);
    }

    const onSearchUserClose = () => {
        setIsSearchModalOpen(false);
    }

    const onConnectedUsers = () => {
        setIsConnectedUsersModalOpen(true);
        getRepo();
    }

    const onConnectedUsersClose = () => {
        setIsConnectedUsersModalOpen(false);
    }


    const getRepo = () => {
        axiosInstance.get("user/connected/")
            .then((res) => {
                const usersConnected = res.data;
                setUsersConnected(usersConnected)
            })
            .catch((err) => {
                console.log(err);
            })
    }

    const getUserActivity = () => {
        let credentilas = {
            username: username,
        }
        console.log(username)
        axiosInstance.get("/user/search/"+username)
            .then(
                res => {
                    console.log(res.data)
                    const userActivity = res.data;
                    setUserActivity(userActivity)
                }
            )
            .catch(error => {
                console.log(error)
            })
    }

    return (<>
        <div className={classes.buttonContainer}>
            <Button className={classes.addButton} onClick={onSearchUser}>Search User</Button>
            <Button className={classes.addButton} onClick={onConnectedUsers}>Connected users</Button>
        </div>
        <TableContainer component={Paper}>
            <Table className={classes.table} aria-label="customized table">
                <TableHead className={classes.tableHead}>
                    <TableRow className={classes.tableRow}>
                        <TableCell className={classes.tableCell}>ID</TableCell>
                        <TableCell className={classes.tableCell}>Username</TableCell>
                        <TableCell className={classes.tableCell}>Operation</TableCell>
                        <TableCell className={classes.tableCell}>Timestamp</TableCell>
                        <TableCell className={classes.buttonSpace}></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody className={classes.tableBody}>
                    {usersList.map((user) => (<>
                        <TableRow className={classes.tableRow} key={user.id}>
                            <TableCell className={classes.tableCell} component="th" scope="row">
                                {user.id}
                            </TableCell>
                            <TableCell className={classes.tableCell}> {user.username}</TableCell>
                            <TableCell className={classes.tableCell}> {user.actiune}</TableCell>
                            <TableCell className={classes.tableCell}> {user.timestamp}</TableCell>
                            <TableCell className={classes.menuButton}>
                            </TableCell>
                        </TableRow >
                    </>
                    ))}
                </TableBody>
            </Table>

        </TableContainer>

        <Dialog open={isSearchModalOpen}>
            <DialogTitle style={{ borderBottom: "1px solid lightGrey" }}>
                <div className={classes.dialogTitle}>
                    <Typography variant="subtitle1">Search User</Typography>
                    <CloseIcon style={{ cursor: "pointer" }} onClick={onSearchUserClose} />
                </div>

            </DialogTitle>
            <DialogContent >
                <div className={classes.section}>
                    <TextField onChange={e => setUsername(e.target.value)} label="username" variant="filled" />

                </div>
                <div className={classes.container}>
                    <Button className={classes.button} onClick={getUserActivity}>Search</Button>
                </div>

                <TableContainer component={Paper}>
                    <Table className={classes.tableUsersActivity} aria-label="customized table">
                        <TableHead className={classes.tableHead}>
                            <TableRow className={classes.tableRow}>
                                <TableCell className={classes.tableCell}>ID</TableCell>
                                <TableCell className={classes.tableCell}>Username</TableCell>
                                <TableCell className={classes.tableCell}>Operation</TableCell>
                                <TableCell className={classes.tableCell}>Timestamp</TableCell>
                                <TableCell className={classes.buttonSpace}></TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody className={classes.tableBody}>
                            {userActivity.map((user) => (<>
                                <TableRow className={classes.tableRow} key={user.id}>
                                    <TableCell className={classes.tableCell} component="th" scope="row">
                                        {user.id}
                                    </TableCell>
                                    <TableCell className={classes.tableCell}> {user.username}</TableCell>
                                    <TableCell className={classes.tableCell}> {user.actiune}</TableCell>
                                    <TableCell className={classes.tableCell}> {user.timestamp}</TableCell>
                                    <TableCell className={classes.menuButton}>
                                    </TableCell>
                                </TableRow >
                            </>
                            ))}
                        </TableBody>
                    </Table>

                </TableContainer>


            </DialogContent>
        </Dialog>

        <Dialog open={isConnectedUsersModalOpen}>
            <DialogTitle style={{ borderBottom: "1px solid lightGrey" }}>
                <div className={classes.dialogTitle}>
                    <Typography variant="subtitle1">Connected Users</Typography>
                    <CloseIcon style={{ cursor: "pointer" }} onClick={onConnectedUsersClose} />
                </div>

            </DialogTitle>
            <DialogContent >
            <div className={classes.section}>
                    <TextField value={usersConnected} label="" variant="filled" />

                </div>
            </DialogContent>
        </Dialog>


    </>
    )


}

export default UserActivityPage;