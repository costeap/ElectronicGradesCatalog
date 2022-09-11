import React from "react";
import axiosInstance from "./axios";
import {
    ListItem,
    List,
    ListItemText,
    Avatar,
    ListItemIcon,
    Button
} from "@material-ui/core";
import EditIcon from '@mui/icons-material/Edit';
import OwnerComponnet from "./Owner";

class Home extends React.Component {
    constructor() {
        super()
        this.state = {
            owners: [],
            displayCars: false
        }
    };


    // componentDidMount() {
    //     axiosInstance
    //         .get(
    //             "/owner",
    //         )
    //         .then(res => {
    //             const val = res.data;
    //             this.setState({
    //                 owners: val
    //             });
    //             console.log(val);
    //             console.log(this.state.owners);
    //         })
    //         .catch(error => {
    //             console.log(error);
    //         });
    // };

    displayCars = () => {
        this.setState({
            displayCars: !this.state.displayCars
        })
        console.log("Buton")
        console.log(this.state.displayCars)

    }

    render() {
        return (
            <React.Fragment>
                <Button onClick={() => this.displayCars()}>
                    <EditIcon /> WITH CARS
                </Button>
                <List>
                    {this.state.owners.map(owner => (
                        <ListItem>
                            <ListItemIcon>
                                <Avatar>
                                    {"O"}
                                </Avatar>
                            </ListItemIcon>
                            <ListItemText primary={owner.id + " " + owner.name} />
                            {this.state.displayCars ? <OwnerComponnet elements={owner.cars} /> : null}

                        </ListItem>
                    ))}
                </List>
            </React.Fragment>
        )
    }

}

export default Home;