import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static final int MATCH_ID = 0;
    private static final int INNING = 1;
    private static final int BATTING_TEAM = 2;
    private static final int BOWLING_TEAM = 3;
    private static final int OVER = 4;
    private static final int BALL = 5;
    private static final int BATSMAN = 6;
    private static final int NON_STRIKER = 7;
    private static final int BOWLER = 8;
    private static final int IS_SUPER_OVER = 9;
    private static final int WIDE_RUNS = 10;
    private static final int BYE_RUNS = 11;
    private static final int LEGBYE_RUNS = 12;
    private static final int NOBALL_RUNS = 13;
    private static final int PENALTY_RUNS = 14;
    private static final int BATSMAN_RUNS = 15;
    private static final int EXTRA_RUNS = 16;
    private static final int TOTAL_RUNS = 17;
    private static final int PLAYER_DISMISSAL = 18;
    private static final int DISMISSAL_KIND = 19;
    private static final int FIELDER = 20;

    private static final int ID = 0;
    private static final int SEASON = 1;
    private static final int CITY = 2;
    private static final int DATE = 3;
    private static final int TEAM1 = 4;
    private static final int TEAM2 = 5;
    private static final int TOSS_WINNER = 6;
    private static final int TOSS_DECISION = 7;
    private static final int RESULT = 8;
    private static final int DL_APPLIED = 9;
    private static final int WINNER = 10;
    private static final int WIN_BY_RUNS = 11;
    private static final int WIN_BY_WICKETS = 12;
    private static final int PLAYER_OF_MATCH = 13;
    private static final int VENUE = 14;
    private static final int UMPIRE1 = 15;
    private static final int UMPIRE2 = 16;

    public static void main(String[] args) throws FileNotFoundException {
        List<Delivery> deliveries = getDeliveriesData();
        List<Match> matches = getMatchesData();

        findNumberOfMatchesPlayedPerYer(matches);
        findNumberOfMatchesWonOfAllTeamsOverAllTheYears(matches);
        findTheExtraRunsConcededPerTeamIn2016(matches, deliveries);
        findTheTopEconomicalBowlersIn2015(matches, deliveries);
        findTheTopFivePartnershipsIn2017(matches, deliveries);
    }

    private static void findTheTopFivePartnershipsIn2017(List<Match> matches, List<Delivery> deliveries) {
        System.out.println("Top 10 Partnerships in 2017");
        System.out.println("---------------------------");

        Set<Integer> matchIds = new HashSet<>();
        for(Match match : matches){
            if(match.getSeason() == 2017) matchIds.add(match.getId());
        }

        Map<String, Integer> runsByPartnerships = new TreeMap<>();
        for(Delivery delivery : deliveries){
            if(matchIds.contains(delivery.getMatchId())){
                List<String> partners = Arrays.asList(delivery.getBatsman(), delivery.getNonStriker());
                Collections.sort(partners);
                String partnersNames = partners.get(0) + " - " + partners.get(1);
                runsByPartnerships.put(partnersNames, runsByPartnerships.getOrDefault(partnersNames,0) + delivery.getBatsmanRuns());
            }
        }

        Map<Integer, List<String>> runsAndPartnerships = new TreeMap<>(Collections.reverseOrder());
        for(Map.Entry<String, Integer> runsByPartnership : runsByPartnerships.entrySet()){
            List<String> playersNames = runsAndPartnerships.getOrDefault(runsByPartnership.getValue(), new ArrayList<>());
            playersNames.add(runsByPartnership.getKey());
            runsAndPartnerships.put(runsByPartnership.getValue(), playersNames);
        }

        int i = 0;
        for(Map.Entry<Integer, List<String>> runsAndPartnership : runsAndPartnerships.entrySet()){
            for(String names : runsAndPartnership.getValue()){
                if(i++ < 10){
                    System.out.println(i + ". " + names + " : " + runsAndPartnership.getKey());
                }
            }
        }
    }

    private static void findTheTopEconomicalBowlersIn2015(List<Match> matches, List<Delivery> deliveries) {
        System.out.println("For The Year 2015 Top Economic Bowlers");
        System.out.println("--------------------------------------------------");

        Set<Integer> matchIds = new HashSet<>();
        for(Match match : matches){
            if(match.getSeason() == 2015){
                matchIds.add(match.getId());
            }
        }

        int runs;
        Map<String, List<Integer>> runsAndBallsByBowlers = new HashMap<>();
        for(Delivery delivery : deliveries){
            if(matchIds.contains(delivery.getMatchId())){
                List<Integer> runsAndBalls = runsAndBallsByBowlers.getOrDefault(delivery.getBowler(), Arrays.asList(0,0));
                if( delivery.getNoballRuns() > 0 || delivery.getWideRuns() > 0){
                    runs = delivery.getBatsmanRuns() + delivery.getNoballRuns() + delivery.getWideRuns();
                }
                else {
                    runs = delivery.getBatsmanRuns();
                    runsAndBalls.set(1, runsAndBalls.get(1) + 1);
                }
                runsAndBalls.set(0, runsAndBalls.get(0) + runs);
                runsAndBallsByBowlers.put(delivery.getBowler(), runsAndBalls);
            }
        }

        Map<Float, ArrayList<String>> economicByBowlers = new TreeMap<>();
        float economic;
        ArrayList<String> bowlersNames;
        for(Map.Entry<String, List<Integer>> runsAndBallsByBowler : runsAndBallsByBowlers.entrySet()){
            economic = (float) Math.round(((float) runsAndBallsByBowler.getValue().get(0) / ((float) runsAndBallsByBowler.getValue().get(1) / 6)) * 100) / 100;
            bowlersNames = economicByBowlers.getOrDefault(economic, new ArrayList<>());
            bowlersNames.add(runsAndBallsByBowler.getKey());
            economicByBowlers.put(economic, bowlersNames);
        }

        int top = 0;
        finish:
        for(Map.Entry<Float, ArrayList<String>> economicByBowler : economicByBowlers.entrySet()){
            for(String topBowler : economicByBowler.getValue()){
                if(top++ < 10){
                    System.out.println( top + ". " + topBowler + " - " + economicByBowler.getKey());
                }
                else {
                    break finish;
                }
            }
        }
    }

    private static void findTheExtraRunsConcededPerTeamIn2016(List<Match> matches, List<Delivery> deliveries) {
        System.out.println("For The Year 2016 Extra Runs Conceded By Each Team");
        System.out.println("--------------------------------------------------");

        TreeSet<Integer> matchIds = new TreeSet<>();
        for(Match match : matches){
            if(match.getSeason() == 2016){
                matchIds.add(match.getId());
            }
        }

        Map<String, Integer> extraRunsConcededPerTeam = new TreeMap<>();
        for(Delivery delivery : deliveries){
            if(matchIds.contains(delivery.getMatchId())){
                int extraRuns = delivery.getExtraRuns();
                extraRunsConcededPerTeam.put(delivery.getBowlingTeam(), extraRunsConcededPerTeam.getOrDefault(delivery.getBowlingTeam(), 0) + extraRuns);
            }
        }

        for(Map.Entry<String, Integer> extraRunsConcededByTeam : extraRunsConcededPerTeam.entrySet()){
            System.out.println(extraRunsConcededByTeam.getKey() + " = " +extraRunsConcededByTeam.getValue());
        }
    }

    private static void findNumberOfMatchesWonOfAllTeamsOverAllTheYears(List<Match> matches) {
        System.out.println("Number Of Matches Won By The Each Team Over All The Years");
        System.out.println("---------------------------------------------------------");
        Map<String, Integer> matchesWonByEachTeam = new TreeMap<>();
        for (Match match : matches) {
            String winner = match.getWinner();
            matchesWonByEachTeam.put(winner, matchesWonByEachTeam.getOrDefault(winner, 0) + 1);
        }

        for (Map.Entry<String, Integer> matchesWonByTeam : matchesWonByEachTeam.entrySet()) {
            if (!matchesWonByTeam.getKey().isEmpty())
                System.out.println(matchesWonByTeam.getKey() + " = " + matchesWonByTeam.getValue());
        }
    }

    private static void findNumberOfMatchesPlayedPerYer(List<Match> matches) {
        System.out.println("Number Of Matches Played Per Year");
        System.out.println("---------------------------------");
        Map<Integer, Integer> matchesPlayedPerYear = new TreeMap<>();
        for (Match match : matches) {
            int season = match.getSeason();
            matchesPlayedPerYear.put(season, matchesPlayedPerYear.getOrDefault(season, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> matchesPlayedInTheYear : matchesPlayedPerYear.entrySet()) {
            System.out.println("Number Of Matches Played in " + matchesPlayedInTheYear.getKey() + " is " + matchesPlayedInTheYear.getValue());
        }
    }

    private static List<Match> getMatchesData() throws FileNotFoundException {
        File file = new File("/home/pran/IdeaProjects/iplProject/dataset/matches.csv");
        Scanner scanner = new Scanner(file);

        List<Match> matches = new ArrayList<>();
        int line = 0;
        while (scanner.hasNextLine()) {
            String[] matchData = scanner.nextLine().split(",");
            if (line++ > 0) {
                Match match = new Match();
                try {
                    match.setId(matchData[ID]);
                    match.setSeason(matchData[SEASON]);
                    match.setCity(matchData[CITY]);
                    match.setDate(matchData[DATE]);
                    match.setTeam1(matchData[TEAM1]);
                    match.setTeam2(matchData[TEAM2]);
                    match.setTossWinner(matchData[TOSS_WINNER]);
                    match.setTossDecision(matchData[TOSS_DECISION]);
                    match.setResult(matchData[RESULT]);
                    match.setDlApplied(matchData[DL_APPLIED]);
                    match.setWinner(matchData[WINNER]);
                    match.setWinByRuns(matchData[WIN_BY_RUNS]);
                    match.setWinByWickets(matchData[WIN_BY_WICKETS]);
                    match.setPlayerOfMatch(matchData[PLAYER_OF_MATCH]);
                    match.setVenue(matchData[VENUE]);
                    match.setUmpire1(matchData[UMPIRE1]);
                    match.setUmpire2(matchData[UMPIRE2]);
                }
                catch (ArrayIndexOutOfBoundsException e) {
                }
                matches.add(match);
            }
        }
        return matches;
    }

    private static List<Delivery> getDeliveriesData() throws FileNotFoundException {
        File file = new File("/home/pran/IdeaProjects/iplProject/dataset/deliveries.csv");
        Scanner scanner = new Scanner(file);

        List<Delivery> deliveries = new ArrayList<>();
        int line = 0;
        while (scanner.hasNextLine()) {
            String[] deliveryData = scanner.nextLine().split(",");
            if (line++ > 0) {
                Delivery delivery = new Delivery();
                try {
                    delivery.setMatchId(deliveryData[MATCH_ID]);
                    delivery.setInning(deliveryData[INNING]);
                    delivery.setBattingTeam(deliveryData[BATTING_TEAM]);
                    delivery.setBowlingTeam(deliveryData[BOWLING_TEAM]);
                    delivery.setOver(deliveryData[OVER]);
                    delivery.setBall(deliveryData[BALL]);
                    delivery.setBatsman(deliveryData[BATSMAN]);
                    delivery.setNonStriker(deliveryData[NON_STRIKER]);
                    delivery.setBowler(deliveryData[BOWLER]);
                    delivery.setIsSuperOver(deliveryData[IS_SUPER_OVER]);
                    delivery.setWideRuns(deliveryData[WIDE_RUNS]);
                    delivery.setByeRuns(deliveryData[BYE_RUNS]);
                    delivery.setLegbyeRuns(deliveryData[LEGBYE_RUNS]);
                    delivery.setNoballRuns(deliveryData[NOBALL_RUNS]);
                    delivery.setPenaltyRuns(deliveryData[PENALTY_RUNS]);
                    delivery.setBatsmanRuns(deliveryData[BATSMAN_RUNS]);
                    delivery.setExtraRuns(deliveryData[EXTRA_RUNS]);
                    delivery.setTotalRuns(deliveryData[TOTAL_RUNS]);
                    delivery.setPlayerDismissal(deliveryData[PLAYER_DISMISSAL]);
                    delivery.setDismissalKind(deliveryData[DISMISSAL_KIND]);
                    delivery.setFielder(deliveryData[FIELDER]);
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                deliveries.add(delivery);
            }
        }
        return deliveries;
    }
}