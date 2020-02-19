import React from "react";

export default class ImageUpload extends React.Component {
    constructor(props) {
        super(props);
        this.state = {};
    }

    onFileChangeHandler = (e) => {
        e.preventDefault();
        this.setState({
            selectedFile: e.target.files[0]
        });
    };

    onSubmit = (e) => {
        alert("beforeStart");
        e.preventDefault();
        const formData = new FormData();
        formData.append('file', this.state.selectedFile);
        alert("start");
        fetch('http://localhost:8080/upload', {
            method: 'post',
            body: formData
        }).then(res => {
            if(res.ok) {
                console.log(res.data);
                alert("File uploaded successfully.")
            }
        });
        alert("end")
    };

    render() {
        return(
            <div className="container">
                <div className="row">
                    <div className="col-md-6">
                        <div className="form-group files color">
                            <label>Upload Your File </label>
                            <form>
                                <input type="file" className="form-control" name="file" onChange={this.onFileChangeHandler}/>
                                <input type="submit" value="Submit" onClick={()=> this.onSubmit()}/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
