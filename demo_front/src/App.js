import logo from './logo.svg';
import './App.css';
import {
    BrowserRouter as Router,
    Route,
    Routes as Switch,
    Navigate as Redirect
} from "react-router-dom";
import Administration from './Administration';
import Login from './Login';
import Home from "./Home";
import Profesor from "./Components/Profesor/Profesor";
import Student from "./Components/Student/Student";
import Admin from "./Components/Admin/Admin";


function App() {
    const defaultRoute = window.location.pathname === "/" ? <Redirect to="/log-in"/> : undefined;

    return (
        // <div className="App">
        //     <header className="App-header">
        //         <img src={logo} className="App-logo" alt="logo"/>
        //         <p>
        //             Edit <code>src/App.js</code> and save to reload.
        //         </p>
        //
        //         <p>
        //           This is PS demo!!
        //         </p>
        //         <a
        //             className="App-link"
        //             href="https://reactjs.org"
        //             target="_blank"
        //             rel="noopener noreferrer"
        //         >
        //             Learn React
        //         </a>
        //     </header>
        // </div>


        <Router>
            <Switch>
                <Route exact path="/log-in" element={<Login/>}/>
                <Route exact path="/administration" element={<Administration/>}/>
                <Route exact path="/home" element={<Home/>}/>
                <Route exact path="/profesor/:nume/:cnp" element={<Profesor/>} />
                <Route exact path="/student/:index" element={<Student/>} />
                <Route exact path="/admin" element={<Admin/>}/>
            </Switch>
            {defaultRoute}
        </Router>
    );
}

export default App;
