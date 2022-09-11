import React, { useEffect, useRef, useState } from 'react';
import { useStyles } from './Style.js';
import { Tabs, Tab, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Tooltip, Menu, MenuItem, Paper, Button, Dialog, DialogTitle, DialogContent, Box } from '@material-ui/core';
import axiosInstance from '../../axios.js';
import MenuIcon from '@material-ui/icons/Menu';

import Header from '../Header/Header';
import { SettingsRemoteSharp } from '@mui/icons-material';
import StudentPage from "./Pages/Student";
import ProfessorPage from "./Pages/Professor";
import CoursePage from "./Pages/Course";
import UserActivityPage from "./Pages/UserActivity";
import * as SockJS from "sockjs-client";
import * as Stomp from "stompjs";




const Admin = () => {

    const classes = useStyles();

    const [tab, setTab] = useState("stud")
    const [studentList, setStudentList] = useState([])
    const [professorList, setProfessorList] = useState([])
    const [courseList, setCourseList] = useState([])
    const [usersList, setUsersList] = useState([])
    const [anchorEl, setAnchorEl] = useState(null);
    const [coursesOpen, setCourseOpen] = useState([])
    const [isAdmin, setIsAdmin] = useState(false)

    const ws = useRef(null);
    const [isPaused, setPause] = useState(false);

    useEffect(() => {
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
    }, []);

    useEffect(() => getRepo(), [])
    const getRepo = () => {
        axiosInstance.get("/student")
            .then((res) => {
                const studList = res.data;
                setStudentList(studList)
            })
            .catch((err) => {
                console.log(err);
            })
        axiosInstance.get("/profesor")
            .then((res) => {
                const profList = res.data;
                setProfessorList(profList)
            })
            .catch((err) => {
                console.log(err);
            })
        axiosInstance.get("/curs")
            .then((res) => {
                const courseList = res.data;
                setCourseList(courseList)
            })
            .catch((err) => {
                console.log(err);
            })
        axiosInstance.get("/user")
            .then((res) => {
                const usersList = res.data;
                setUsersList(usersList)
            })
            .catch((err) => {
                console.log(err);
            })
    }

    useEffect (() => {
        setIsAdmin (localStorage.getItem("role"))
        console.log(isAdmin)
    }, [])

    const changePage = (page) => {
        setTab(page)
    }

    const onMenuClick = (event) => {
        event.stopPropagation();
        setAnchorEl(event.currentTarget);
    };

    const onMenuClose = (e) => {
        e.stopPropagation();
        setAnchorEl(null);
    };

    const onViewCourses = (event) => {

    };

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
            {isAdmin=="true" && 
            (<Box><Header userType="Admin" />
            <Tabs className={classes.tabs} value={tab}>
                <Tab label="Students" onClick={() => changePage("stud")} value="stud" />
                <Tab label="Proffesors" onClick={() => changePage("prof")} value="prof" />
                <Tab label="Courses" onClick={() => changePage("course")} value="course" />
                <Tab label="Users Activity" onClick={() => changePage("users")} value="users" />
            </Tabs> 

            {tab == "stud" &&
                <StudentPage studentList={studentList} />
            }
            {tab == "prof" &&
                <ProfessorPage professorList={professorList} />
            }
            {tab == "course" &&
                <CoursePage courseList={courseList} />
            }
            {tab == "users" &&
                <UserActivityPage usersList={usersList} />
            }
        
        </Box>
            )}
        </div>

    )
}


export default Admin;