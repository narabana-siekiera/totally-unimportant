package automata;

/**
 * Klasa sluzaca do budowania tymczasowego automatu z wyrazenia regularnego.
 */
public class RegExAutomatonBuilder {

	private RegExAutomaton joinLeftLast(RegExAutomaton left, RegExAutomaton last) {
		if (left == null)
			return last;
		if (last != null)
			left.concatenate(last);
		return left;
	}

	RegExAutomaton buildAutomaton(String regEx) {
		RegExAutomaton left = null;
		RegExAutomaton last = null;

		if (regEx == null || regEx.isEmpty()) {
			return new RegExAutomaton(null);
		}
		if (regEx.startsWith(")") || regEx.startsWith("*") || regEx.startsWith("+"))
			throw new IllegalArgumentException();

		int pos = 0;
		while (pos < regEx.length()) {
			char cur = regEx.charAt(pos);
			switch (cur) {
			case '*':
				last.star();
				break;
			case '(':
				left = joinLeftLast(left, last);
				int close = regEx.indexOf(')', pos);
				last = buildAutomaton(regEx.substring(pos + 1, close)); // !
				pos = close;
				break;
			case ')':
				throw new IllegalArgumentException();
			case '+':
				if (pos + 1 == regEx.length())
					throw new IllegalArgumentException();
				left = joinLeftLast(left, last);
				last = buildAutomaton(regEx.substring(pos + 1));
				left.sum(last);
				last = null;
				pos = regEx.length() - 1;
				break;
			default:
				left = joinLeftLast(left, last);
				last = new RegExAutomaton(new Symbol(String.valueOf(cur)));
				break;
			}
			++pos;
		}
		return joinLeftLast(left, last);
	}
}
