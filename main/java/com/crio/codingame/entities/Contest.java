    
package com.crio.codingame.entities;

import java.util.List;
import java.util.stream.Collectors;

import com.crio.codingame.exceptions.InvalidContestException;

public class Contest extends BaseEntity{
    private final String name;
    private final List<Question> questions;
    private final Level level;
    private final User creator;
    private ContestStatus contestStatus;

    public Contest(Contest contest){
        this(contest.id,contest.name,contest.questions,contest.level,contest.creator,contest.contestStatus);
    }

    public Contest(String id, String name, List<Question> questions, Level level, User creator,
            ContestStatus contestStatus) {
        this(name,questions,level,creator,contestStatus);
        this.id = id;
    }

    public Contest(String name, List<Question> questions, Level level, User creator,
            ContestStatus contestStatus) {
        this.name = name;
        this.questions = questions;
        validateQuestionList(questions, level);
        this.level = level;
        this.creator = creator;
        this.contestStatus = contestStatus;
    }


    // private void validateQuestionList(List<Question> qList, Level contestLevel) throws InvalidContestException {
    //     Level level;
    //     level = qList.get(0).getLevel();
    //     for(Question question : qList) {
    //         if(question.getLevel() == level && question.getLevel() == contestLevel) {
    //             continue;
    //         }
    //         else{
    //             throw new InvalidContestException("The contest level does not match the question level");
    //         }
    //     }
    // }

    
    private void validateQuestionList(List<Question> qList, Level contestLevel) throws InvalidContestException {
        Level level;
        level = qList.get(0).getLevel();
        for(Question question : qList) {
            if(question.getLevel() == level && question.getLevel() == contestLevel) {
                continue;
            }
            else{
                throw new InvalidContestException("The contest level does not match the question level");
            }
        }
    }
    

    // TODO: CRIO_TASK_MODULE_SERVICES
    // Change the Contest Status to ENDED

    public void endContest(){
        this.contestStatus=ContestStatus.ENDED;
    }
    
    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions.stream().collect(Collectors.toList());
    }

    public Level getLevel() {
        return level;
    }

    public User getCreator() {
        return creator;
    }

    public ContestStatus getContestStatus() {
        return contestStatus;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contest other = (Contest) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Contest [id=" + id + ", name=" + name + ", level=" + level + ", creator=" + creator.getName() + ", contestStatus=" + contestStatus + ", questions=" + questions + "]";
    }

}

