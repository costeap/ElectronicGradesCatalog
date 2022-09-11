import React from "react";
import {
    ListItem,
    List,
    ListItemText,
    Avatar,
    ListItemIcon,
} from "@material-ui/core";


class Owner extends React.Component {
    constructor() {
        super();
    }

    render() {
        return (
            <React.Fragment>
                <List>
                    {this.props.elements.map(el => (
                        <ListItem key={el.id}>
                            <ListItemIcon>
                                <Avatar>{"OC"}</Avatar>
                            </ListItemIcon>
                            <ListItemText
                                primary={el.id + " " + el.maker} />

                        </ListItem>
                    ))}
                </List>
            </React.Fragment>
        )

    }
}

export default Owner;