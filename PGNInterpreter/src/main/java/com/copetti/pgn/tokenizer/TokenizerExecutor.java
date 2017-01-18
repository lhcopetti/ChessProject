package com.copetti.pgn.tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class TokenizerExecutor {

	private List<Function<String, Optional<? extends TokenInterface>>> tokenizers;

	public TokenizerExecutor() {
		tokenizers = new ArrayList<>();
	}

	public void addTokenizer(Function<String, Optional<? extends TokenInterface>> tokenizer) {
		tokenizers.add(tokenizer);
	}

	public Optional<PGNToken> execute(String characters) {

		if (characters.isEmpty())
			return Optional.empty();

		String input = characters.substring(0, 1);

		Optional<? extends TokenInterface> apply = tokenizers.stream() //
				.map(x -> x.apply(input)) //
				.filter(x -> x.isPresent()) //
				.findFirst().orElse(Optional.empty());

		if (!apply.isPresent()) {
			return Optional.empty();
		}

		TokenInterface ti = apply.get();
		Optional<TokenTypes> tokenType = TokenTypes.from(ti);

		if (!tokenType.isPresent()) {
			return Optional.empty();
		}

		return Optional.of(PGNToken.of(tokenType.get(), ti.getValue()));
	}

}
