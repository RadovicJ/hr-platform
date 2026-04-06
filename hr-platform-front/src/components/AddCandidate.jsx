import { useState } from "react";

function CreateCandidatePage() {
    const [fullname, setFullName] = useState("");
    const [birthDate, setBirthDate] = useState("");
    const [contact, setContact] = useState("");
    const [email, setEmail] = useState("");

    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setMessage("");

        try {
            const response = await fetch("http://localhost:8080/candidate/add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    fullname,
                    birthDate,
                    contact,
                    email
                })
            });

            if (!response.ok) {
                throw new Error("Failed to create candidate");
            }

            await response.json();

            setMessage("Candidate created successfully!");

            setFullName("");
            setBirthDate("");
            setContact("");
            setEmail("");

        } catch (error) {
            setMessage(error.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <hr></hr>
            <h2>Add New Candidate</h2>

            <form onSubmit={handleSubmit}>

                <div>
                    <label>Full Name:</label>
                    <input
                        type="text"
                        value={fullname}
                        onChange={(e) => setFullName(e.target.value)}
                        required
                    />
                </div>

                <div>
                    <label>Birth Date:</label>
                    <input
                        type="date"
                        value={birthDate}
                        onChange={(e) => setBirthDate(e.target.value)}
                        required
                    />
                </div>

                <div>
                    <label>Phone:</label>
                    <input
                        type="text"
                        value={contact}
                        onChange={(e) => setContact(e.target.value)}
                        required
                    />
                </div>

                <div>
                    <label>Email:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>

                <button type="submit" disabled={loading}>
                    {loading ? "Sending..." : "Add Candidate"}
                </button>

            </form>

            {message && <p>{message}</p>}
        </div>
    );
}

export default CreateCandidatePage;