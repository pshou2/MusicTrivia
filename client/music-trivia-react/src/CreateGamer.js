import { useState } from "react";

function CreateGamer(){
    const [gamer, setGamer] = useState({
        gamer_tag: '',
        tagline: ''
    });

    //handle methods
    const handleSubmit = (event) => {
        event.preventDefault();
    };

    return (
        <>
            <div className="container-fluid">
                <h1>CREATE YOUR GAMER</h1>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="gamer_tag" className="form-label">Enter your Name: </label>
                        <input
                        type="text"
                        className="form-control"
                        id="gamer_tag"
                        name="gamer_tag"
                        placeholder="GAMER420"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="tagline" className="form-label">Tagline: </label>
                        <textarea id="tagline" name="tagline"></textarea>
                    </div>
                    <div>
                        <button type="submit" className="btn btn-primary">CREATE GAMER</button>
                    </div>
                </form>
            </div>
        </>
    );
}

export default CreateGamer;