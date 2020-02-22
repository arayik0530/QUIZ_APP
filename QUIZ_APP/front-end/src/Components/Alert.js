import React from 'react';
import Snackbar from '@material-ui/core/Snackbar';
import MuiAlert from '@material-ui/lab/Alert';
function AlertComp(props) {
    return <MuiAlert elevation={6} variant="filled" {...props} />;
  }
export default function Alert(props)
{  const [open, setOpen] = React.useState(true);
    const handleClose = () => {
        setOpen(false);
      };
    return ( <Snackbar open={open} autoHideDuration={6000} onClose={handleClose}>
        <AlertComp onClose={handleClose} severity="success">
          {props.message}
        </AlertComp>
      </Snackbar>)
}