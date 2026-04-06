import React, { useState } from "react";

function GetByFullname() {
    const [fullname, setFullname] = useState("");
    const [candidate, setCandidate] = useState(null);
    const [error, setError] = useState(null);

    const handleSearch = () => {
        const url = `http://localhost:8080/candidate/getByFullname?fullname=${encodeURIComponent(fullname)}`;

        fetch(url)
            .then(res => {
                if (res.status === 400) {
                    throw new Error("Enter candidate name");
                } else if (!res.ok) {
                    throw new Error("Candidate not found");
                }
                return res.json();
            })
            .then(data => {
                setCandidate(data);
                setError(null);
            })
            .catch(err => {
                console.error(err);
                setCandidate(null);
                setError(err.message);
            });
    };

    return (

        <div>
            <hr></hr>
            <h2>Get Candidate by Name</h2>

            <input
                type="text"
                placeholder="Enter full name"
                value={fullname}
                onChange={e => setFullname(e.target.value)}
            />
            <button onClick={handleSearch} style={{marginLeft: "10px"}}>
                Search
            </button>

            {error && <p style={{color: "red"}}>{error}</p>}

            {candidate && (
                <div>
                    <h2>Candidate Info</h2>
                    <p><strong>Name:</strong> {candidate.fullname}</p>
                    <p><strong>Email:</strong> {candidate.email}</p>
                    <p><strong>Phone:</strong> {candidate.contact}</p>
                </div>
            )}
        </div>
    );
}

export default GetByFullname;