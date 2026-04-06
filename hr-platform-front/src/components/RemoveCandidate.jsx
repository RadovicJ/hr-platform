import { useState, useEffect } from "react";

function DeleteCandidatePage() {
    const [candidates, setCandidates] = useState([]);
    const [selectedCandidate, setSelectedCandidate] = useState(null);
    const [loading, setLoading] = useState(false);
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

        fetchCandidates();
    }, []);

    const handleDelete = async (e) => {
        e.preventDefault();
        if (!selectedCandidate) {
            setMessage("Please select a candidate to delete");
            return;
        }

        setLoading(true);
        setMessage("");

        try {
            const response = await fetch(
                `http://localhost:8080/candidate/remove?id=${selectedCandidate.id}`,
                { method: "DELETE" }
            );

            if (!response.ok) throw new Error("Failed to delete candidate");

            setMessage(`Candidate "${selectedCandidate.fullname}" deleted successfully!`);

            setCandidates(candidates.filter(c => c.id !== selectedCandidate.id));
            setSelectedCandidate(null);
        } catch (error) {
            setMessage(error.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <hr></hr>
            <h2>Delete Candidate</h2>

            <form onSubmit={handleDelete}>
                <div>
                    <label>Select Candidate:</label>
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
                        <option value="">-- Select --</option>
                        {candidates.map((candidate) => (
                            <option key={candidate.id} value={candidate.id}>
                                {candidate.fullname}
                            </option>
                        ))}
                    </select>
                </div>

                <button type="submit" disabled={loading}>
                    {loading ? "Deleting..." : "Delete Candidate"}
                </button>
            </form>

            {message && <p>{message}</p>}
        </div>
    );
}

export default DeleteCandidatePage;