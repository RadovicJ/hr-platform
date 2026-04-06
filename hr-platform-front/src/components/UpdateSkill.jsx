import React, { useEffect, useState } from "react";

const UpdateCandidateSkill = () => {
    const [candidates, setCandidates] = useState([]);
    const [skills, setSkills] = useState([]);
    const [selectedCandidate, setSelectedCandidate] = useState("");
    const [selectedSkill, setSelectedSkill] = useState("");
    const [message, setMessage] = useState("");

    useEffect(() => {
        const fetchCandidates = async () => {
            try {
                const response = await fetch("http://localhost:8080/candidate/all");
                if (!response.ok) throw new Error("Failed to fetch candidates");
                const data = await response.json();
                setCandidates(data);
            } catch (error) {
                setMessage(error.message);
            }
        };
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
        fetchCandidates();
        fetchSkills();
    }, []);

    const handleUpdate = async (e) => {
        e.preventDefault();

        if (!selectedCandidate || !selectedSkill) {
            setMessage("Please select both candidate and skill.");
            return;
        }

        try {
            const res = await fetch("http://localhost:8080/skill/update", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    candidateId: selectedCandidate.id,
                    skillId: selectedSkill.id
                }),
            });

            if (res.ok) {
                setMessage("Skill updated successfully!");
            } else {
                setMessage("Failed to update skill.");
            }
        } catch (err) {
            console.error(err);
            setMessage("Error occurred.");
        }
    };

    return (
        <div>
            <hr></hr>
            <h2>Update Candidate Skill</h2>

            <form onSubmit={handleUpdate}>
                <div>
                    <label>Candidate:</label>
                    <select
                        value={selectedCandidate ? selectedCandidate.id : ""}
                        onChange={(e) => {
                            const selectedId = isNaN(e.target.value)
                                ? e.target.value
                                : Number(e.target.value);
                            const candidate = candidates.find(c => c.id === selectedId);
                            setSelectedCandidate(candidate || null);
                        }}
                        required
                    >
                        <option value="">-- Select Candidate --</option>
                        {candidates.map((c) => (
                            <option key={c.id} value={c.id}>
                                {c.fullname}
                            </option>
                        ))}
                    </select>
                </div>

                <div>
                    <label>Skill:</label>
                    <select
                        value={selectedSkill ? selectedSkill.id : ""}
                        onChange={(e) => {
                            const selectedId = isNaN(e.target.value)
                                ? e.target.value
                                : Number(e.target.value);
                            const skill = skills.find(c => c.id === selectedId);
                            setSelectedSkill(skill || null);
                        }}
                        required
                    >
                        <option value="">-- Select Skill --</option>
                        {skills.map((s) => (
                            <option key={s.id} value={s.id}>
                                {s.skillName}
                            </option>
                        ))}
                    </select>
                </div>

                <button type="submit">Update Skill</button>
            </form>

            {message && <p>{message}</p>}
        </div>
    );
};

export default UpdateCandidateSkill;