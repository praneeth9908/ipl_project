
public class Delivery {
    private String matchId;
    private String inning;
    private String battingTeam;
    private String bowlingTeam;
    private String over;
    private String ball;
    private String batsman;
    private String nonStriker;
    private String bowler;
    private String isSuperOver;
    private String wideRuns;
    private String byeRuns;
    private String legbyeRuns;
    private String noballRuns;
    private String penaltyRuns;
    private String batsmanRuns;
    private String extraRuns;
    private String totalRuns;
    private String playerDismissal;
    private String dismissalKind;
    private String fielder;

    public int getMatchId() {
        return Integer.parseInt(matchId);
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public int getInning() {
        return Integer.parseInt(inning);
    }

    public void setInning(String inning) {
        this.inning = inning;
    }

    public String getBattingTeam() {
        return battingTeam;
    }

    public void setBattingTeam(String battingTeam) {
        this.battingTeam = battingTeam;
    }

    public String getBowlingTeam() {
        return bowlingTeam;
    }

    public void setBowlingTeam(String bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }

    public int getOver() {
        return Integer.parseInt(over);
    }

    public void setOver(String over) {
        this.over = over;
    }

    public int getBall() {
        return Integer.parseInt(ball);
    }

    public void setBall(String ball) {
        this.ball = ball;
    }

    public String getBatsman() {
        return batsman;
    }

    public void setBatsman(String batsman) {
        this.batsman = batsman;
    }

    public String getNonStriker() {
        return nonStriker;
    }

    public void setNonStriker(String nonStriker) {
        this.nonStriker = nonStriker;
    }

    public String getBowler() {
        return bowler;
    }

    public void setBowler(String bowler) {
        this.bowler = bowler;
    }

    public boolean getIsSuperOver() {
        return Boolean.parseBoolean(isSuperOver);
    }

    public void setIsSuperOver(String isSuperOver) {
        this.isSuperOver = isSuperOver;
    }

    public int getWideRuns() {
        return Integer.parseInt(wideRuns);
    }

    public void setWideRuns(String wideRuns) {
        this.wideRuns = wideRuns;
    }

    public int getByeRuns() {
        return Integer.parseInt(byeRuns);
    }

    public void setByeRuns(String byeRuns) {
        this.byeRuns = byeRuns;
    }

    public int getLegbyeRuns() {
        return Integer.parseInt(legbyeRuns);
    }

    public void setLegbyeRuns(String legbyeRuns) {
        this.legbyeRuns = legbyeRuns;
    }

    public int getNoballRuns() {
        return Integer.parseInt(noballRuns);
    }

    public void setNoballRuns(String noballRuns) {
        this.noballRuns = noballRuns;
    }

    public int getPenaltyRuns() {
        return Integer.parseInt(penaltyRuns);
    }

    public void setPenaltyRuns(String penaltyRuns) {
        this.penaltyRuns = penaltyRuns;
    }

    public int getBatsmanRuns() {
        return Integer.parseInt(batsmanRuns);
    }

    public void setBatsmanRuns(String batsmanRuns) {
        this.batsmanRuns = batsmanRuns;
    }

    public int getExtraRuns() {
        return Integer.parseInt(extraRuns);
    }

    public void setExtraRuns(String extraRuns) {
        this.extraRuns = extraRuns;
    }

    public int getTotalRuns() {
        return Integer.parseInt(totalRuns);
    }

    public void setTotalRuns(String totalRuns) {
        this.totalRuns = totalRuns;
    }

    public String getPlayerDismissal() {
        return playerDismissal;
    }

    public void setPlayerDismissal(String playerDismissal) {
        this.playerDismissal = playerDismissal;
    }

    public String getDismissalKind() {
        return dismissalKind;
    }

    public void setDismissalKind(String dismissalKind) {
        this.dismissalKind = dismissalKind;
    }

    public String getFielder() {
        return fielder;
    }

    public void setFielder(String fielder) {
        this.fielder = fielder;
    }
}
