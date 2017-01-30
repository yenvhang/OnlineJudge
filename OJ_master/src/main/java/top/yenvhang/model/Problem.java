package top.yenvhang.model;

import java.util.Date;

public class Problem {
	private long problem_id;
	private String title;
	private String content;
	private String input;
	private String output;
	private String input_sample;
	private String output_sample;
	private String hint;
	private Date join_date;
	private String author;
	private int memory_limit;
	private int time_limit=300;
	private int submitted;
	private int solved;
	private String level;
	private int score;
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public long getProblem_id() {
		return problem_id;
	}
	public void setProblem_id(long problem_id) {
		this.problem_id = problem_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getTime_limit() {
		return time_limit;
	}
	public void setTime_limit(int time_limit) {
		this.time_limit = time_limit;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getInput_sample() {
		return input_sample;
	}
	public void setInput_sample(String input_sample) {
		this.input_sample = input_sample;
	}
	public String getOutput_sample() {
		return output_sample;
	}

	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		this.hint = hint;
	}
	public Date getJoin_date() {
		return join_date;
	}
	public void setOutput_sample(String output_sample) {
		this.output_sample = output_sample;
	}
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getMemory_limit() {
		return memory_limit;
	}
	public void setMemory_limit(int memory_limit) {
		this.memory_limit = memory_limit;
	}
	
	public void setRuntime_limit(int runtime_limit) {
		this.time_limit = runtime_limit;
	}
	public int getSubmitted() {
		return submitted;
	}
	public void setSubmitted(int submitted) {
		this.submitted = submitted;
	}
	public int getSolved() {
		return solved;
	}
	public void setSolved(int solved) {
		this.solved = solved;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
}
