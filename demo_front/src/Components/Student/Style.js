import { makeStyles } from "@material-ui/core";

export const useStyles = makeStyles(() => ({
    tabs:{
        boxShadow: "5px 0 5px rgb(0 0 0 / 20%)",
    },
    table:{
        margin:"40px auto", 
        maxWidth: "70% !important",
    },
    tableHead:{
        backgroundColor:"#FF8E53"
    },
    tableBody:{
        fontSize:14
    },
    tableRow:{
        "&:nth-child(even)":{
            backgroundColor:"lightGrey"
        }
    },
    tableCell:{
        maxWidth:50
    },
    dialogTitle:{
        display:"flex",
        justifyContent:"space-between",
        alignItems:"center",
        minWidth:500
    },
    dialogTable:{
        margin:"0 auto"
    },
    section:{
        display:"flex",
        justifyContent:"space-around",
        alignItems:"center",
        marginBottom:10
    },
    addButton:{
        backgroundColor:"#FF8E53"
    },
    buttonContainer:{
        margin:"auto",
        maxWidth:"fit-content",
        marginTop:20
    }
}));