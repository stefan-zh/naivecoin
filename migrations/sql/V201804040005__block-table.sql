CREATE TABLE "block" (
  `index` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  `hash` TEXT NOT NULL UNIQUE,
  `previous_hash` TEXT NOT NULL UNIQUE,
  `timestamp` NUMERIC NOT NULL,
  `nonce` INTEGER NOT NULL,
  FOREIGN KEY(`previous_hash`) REFERENCES `block`(`hash`)
);

CREATE TABLE "tx" (
  `id` TEXT NOT NULL UNIQUE,
  `block_hash` TEXT NOT NULL,
  `hash` TEXT NOT NULL UNIQUE,
  `type` TEXT NOT NULL,
  PRIMARY KEY(`id`),
  FOREIGN KEY(`block_hash`) REFERENCES `block`(`hash`)
);
