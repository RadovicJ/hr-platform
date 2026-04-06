import React, { useEffect, useState } from "react";

const CandidatesBySkills = () => {
    const [skills, setSkills] = useState([]);
    const [selectedSkills, setSelectedSkills] = useState([]);
    const [candidates, setCandidates] = useState([]);
    const [message, setMessage] = useState("");

    useEffect(() => {
        const fetchSkills = async () => {
            try {
                const response = await fetch("http://localhost:8080/skill/all");
                if (!response.ok) throw new Error("Failed to fetch skills");
                const data = await response.json();
                setSkills(data);
            } catch (error) {
                setMessage(error.message);
            }
        };

        fetchSkills();
    }, []);

    const handleSkillChange = (e) => {
        const options = e.target.options;
        const values = [];

        for (let i = 0; i < options.length; i++) {
            if (options[i].selected) {
                values.push(Number(options[i].value));
            }
        }

        setSelectedSkills(values);
    };

    const fetchCandidates = async (e) => {
        e.preventDefault();

        if (selectedSkills.length === 0) {
            setMessage("Select at least one skill.");
            return;
        }

        try {
            const res = await fetch("http://localhost:8080/candidate/getWithSkills", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(selectedSkills),
            });

            const data = await res.json();
            setCandidates(data);
            setMessage("");
        } catch (err) {
            console.error(err);
            setMessage("Error fetching candidates.");
        }
    };

    return (
        <div>
            <hr></hr>
            <h2>Find Candidates by Skills</h2>

            <form onSubmit={fetchCandidates}>
                <div>
                    <label>Skills:</label>
                    <select multiple onChange={handleSkillChange}>
                        {skills.map((s) => (
                            <option key={s.id} value={s.id}>
                                {s.skillName}
                            </option>
                        ))}
                    </select>
                </div>

                <button type="submit">Search</button>
            </form>

            {message && <p>{message}</p>}

            {candidates.length === 0 && selectedSkills.length !== 0 && (
                <p>No candidates found</p>
            )}

            {candidates.length !== 0 && (
                <div>
                    <h3>Results:</h3>
                    <ul>
                        {candidates.map((c) => (
                           <li key={c.id}>{c.fullname}</li>
                       ))}
                    </ul>
                </div>
            )}
        </div>
    );
};

export default CandidatesBySkills;