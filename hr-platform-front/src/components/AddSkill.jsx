import { useState } from "react";

function AddSkill() {
    const [skillName, setSkillName] = useState("");

    const [loading, setLoading] = useState(false);
    const [message, setMessage] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setMessage("");

        try {
            const response = await fetch("http://localhost:8080/skill/add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    skillName
                })
            });

            if (!response.ok) {
                throw new Error("Failed to create skill");
            }

            await response.json();

            setMessage("Skill created successfully!");

            setSkillName("");

        } catch (error) {
            setMessage(error.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <hr></hr>
            <h2>Add New Skill</h2>

            <form onSubmit={handleSubmit}>

                <div>
                    <label>Skill:</label>
                    <input
                        type="text"
                        value={skillName}
                        onChange={(e) => setSkillName(e.target.value)}
                        required
                    />
                </div>

                <button type="submit" disabled={loading}>
                    {loading ? "Sending..." : "Add Skill"}
                </button>

            </form>

            {message && <p>{message}</p>}
        </div>
    );
}

export default AddSkill;