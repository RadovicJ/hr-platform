import AddCandidate from "./components/AddCandidate.jsx";
import GetByFullname from "./components/GetByFullname.jsx";
import RemoveCandidate from "./components/RemoveCandidate.jsx";
import AddSkill from "./components/AddSkill.jsx";
import UpdateSkill from "./components/UpdateSkill.jsx";
import GetCandidatesWithSkills from "./components/GetCandidatesWithSkills.jsx";
import RemoveSkill from "./components/RemoveSkill.jsx";

function App() {
    return (
        <div>
            <h1>HR Platform</h1>
            <AddCandidate />
            <AddSkill />
            <UpdateSkill />
            <RemoveSkill />
            <RemoveCandidate />
            <GetByFullname />
            <GetCandidatesWithSkills />
        </div>
    );
}

export default App;